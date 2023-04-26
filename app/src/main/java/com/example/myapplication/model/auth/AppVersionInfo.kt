package com.example.myapplication.model.auth

import com.squareup.moshi.Json

data class AppVersionInfo(
    @Json(name = "OS") val OS: String = "android",
    @Json(name = "OSVersion") val OSVersion: String, //OS version
    @Json(name = "appVersion") val appVersion: String //app version
)