package com.example.myapplication.model.auth

import com.squareup.moshi.Json

data class Device(
    @Json(name = "vendor") var vendor: String? = "",
    @Json(name = "model") var model: String? = "",
    @Json(name = "SDKVersion") var SDKVersion: String? = "",
    @Json(name = "OS") var OS: String? = "",
    @Json(name = "OSVersion") var OSVersion: String? = "",
    @Json(name = "appVersion") var appVersion: String? = "",
)
