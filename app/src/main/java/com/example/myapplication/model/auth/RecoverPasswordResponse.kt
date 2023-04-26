package com.example.myapplication.model.auth

import com.squareup.moshi.Json

data class RecoverPasswordResponse(
    @Json(name = "data") val data: Boolean = false,
    @Json(name = "statusCode") val statusCode: Int? = null
)