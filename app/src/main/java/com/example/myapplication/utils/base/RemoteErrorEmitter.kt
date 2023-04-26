package com.example.myapplication.utils.base

interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
    fun onError(errors: Map<String, Array<String?>>?)
}