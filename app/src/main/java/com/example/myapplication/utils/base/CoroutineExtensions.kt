package com.example.myapplication.utils.base

import com.example.myapplication.R
import com.example.myapplication.model.ApiCallError
import com.example.myapplication.model.BaseResponse
import com.example.myapplication.model.ErrorData
import com.example.myapplication.utils.getString
import com.google.gson.Gson
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import retrofit2.Response

sealed class ResultHandler<out T : Any?> {
    data class Success<out T : Any>(val data: T) : ResultHandler<T>()
    data class Error(val msg: String) : ResultHandler<Nothing>()
    data class ApiError(val errorCode: Int, val msg: String) : ResultHandler<Nothing>()
    object Loading : ResultHandler<Nothing>()
}

suspend fun <T : Any?> runApiCall(apiCall: suspend () -> Response<T>): ResultHandler<T> =
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let { return ResultHandler.Success(it) }
        }

        try {
            val errorResponseString = response.errorBody()?.string()
            val res = Gson().fromJson(errorResponseString, ApiCallError::class.java)
            errorApi(response.code(), res?.error?.userMessage ?: "")
        } catch (e: Exception) {
            e.printStackTrace()
            errorApi(response.code(), response.message())
        }
    } catch (e: IllegalStateException) {
        e.printStackTrace()
        error(500, e.message ?: e.toString())
    } catch (e: Exception) {
        e.printStackTrace()
        error(500, e.message ?: e.toString())
    }

suspend fun <Q> FlowCollector<DataState<Q>>.runRemoteApiCall(
    success: suspend ApiResponse.Success<BaseResponse<Q>>.() -> Unit,
    error: suspend ApiResponse.Failure.Error<BaseResponse<Q>>.() -> Unit = {
        try {
            val jObjError = errorBody?.string()?.let { JSONObject(it) }

            val errorObject = jObjError?.getString("error")
            val convert = Gson().fromJson(errorObject, ErrorData::class.java)
            val otherError = convert.message.first()
            emit(DataState.OtherError(otherError))
        } catch (e: Exception) {
            emit(DataState.OtherError(getString(R.string.something_unexpected_happened)))
        }
    },
    exception: suspend ApiResponse.Failure.Exception<BaseResponse<Q>>.() -> Unit = {
        this.exception.printStackTrace()
        if (this.exception.message!!.contains("Unable to resolve host"))
            emit(DataState.OtherError(getString(R.string.we_are_unable_to_process_your_request)))
        else emit(DataState.Error(this.exception))
    },
    apiCall: suspend () -> ApiResponse<BaseResponse<Q>>,
) =
    this.run {
        apiCall.invoke()
            .suspendOnSuccess(success)
            .suspendOnError(error)
            .suspendOnException(exception)
    }


suspend fun <Q> FlowCollector<DataState<Q>>.runRemoteApiCallV3(
    success: suspend ApiResponse.Success<Q>.() -> Unit,
    error: suspend ApiResponse.Failure.Error<Q>.() -> Unit = {
        val data = errorBody?.string()
        try {
            val jObjError = JSONObject(data)
            val errorMessage = jObjError.getJSONObject("error").getString("message")
            emit(DataState.OtherError(errorMessage))
        } catch (e: Exception) {
            try {
                val jObjError = JSONObject(data)
                val errorMessage = jObjError.getString("message")
                emit(DataState.OtherError(errorMessage))
            } catch (e: Exception) {
                emit(DataState.OtherError(getString(R.string.something_unexpected_happened)))
            }
        }
    },
    exception: suspend ApiResponse.Failure.Exception<Q>.() -> Unit = {
        if (this.exception.message!!.contains("Unable to resolve host"))
            emit(DataState.OtherError(getString(R.string.we_are_unable_to_process_your_request)))
        else emit(DataState.Error(this.exception))
    },
    apiCall: suspend () -> ApiResponse<Q>,
) =
    this.run {
        apiCall.invoke()
            .suspendOnSuccess(success)
            .suspendOnError(error)
            .suspendOnException(exception)
    }


private fun <T : Any> errorApi(code: Int, msg: String): ResultHandler<T> =
    ResultHandler.ApiError(code, msg)

private fun <T : Any> error(code: Int, msg: String): ResultHandler<T> =
    ResultHandler.Error("Api call failed $msg")

fun <T : Any?> ResultHandler<T>.mapApiCall(
    onError: ((code: Int, msg: String) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onSuccess: ((data: T) -> Unit)? = null
) {

    when (this) {
        is ResultHandler.ApiError -> {
            onError?.invoke(this.errorCode, this.msg)
        }

        ResultHandler.Loading -> {
            onLoading?.invoke()
        }

        is ResultHandler.Success -> {
            onSuccess?.invoke(this.data)
        }

        else -> {}
    }
}

suspend fun <T> Flow<ResultHandler<T>>.collectAndRun(
    onError: ((code: Int, msg: String) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onSuccess: ((data: T) -> Unit)? = null
) {
    this.collectLatest {
        it.mapApiCall(onError, onLoading, onSuccess)
    }
}

suspend fun <T : Any> Flow<ResultHandler<T>>.mapAndRun(
    onError: ((code: Int, msg: String) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onSuccess: ((data: T) -> Unit)? = null
) {
    this.map {
        it.mapApiCall(onError, onLoading, onSuccess)
    }.collectLatest {

    }
}
