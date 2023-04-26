package com.example.myapplication.model.auth

import com.squareup.moshi.Json


class SignUpParams(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "newsletter") val newsletter: Boolean,
)