package com.example.myapplication.model.auth

import com.squareup.moshi.Json


data class LoginParams(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)