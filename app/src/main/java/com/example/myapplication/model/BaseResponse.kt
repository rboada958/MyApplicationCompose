package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
    val statusCode: Int? = 0,
    val data : T,
    val error : ApiCallError? = null,
    val paging: PagingInfo? =null
)

class PagingInfo(
    val totalPages: Int,
    val currentPage: Int,
    val itemsPerPage: Int,
    val totalItems: Int
)

class ApiCallError(
    val error : ApiError,
)

class ApiError(
    val userMessage: String?
)

data class ErrorData(
    @SerializedName("message") val message: List<String>
)