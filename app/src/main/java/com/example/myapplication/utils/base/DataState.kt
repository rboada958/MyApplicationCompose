package com.example.myapplication.utils.base

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Throwable) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    data class OtherError(val error: String) : DataState<Nothing>()
    data class TypedError<out T>(val error: T) : DataState<Nothing>()
    data class CodeError(val code: String) : DataState<Nothing>()
}