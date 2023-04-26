package com.example.myapplication.model.auth

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "accessToken") var accessToken: String?,
    @Json(name = "expires") var expires: Long?,
    @Json(name = "user") var user: User?
)
