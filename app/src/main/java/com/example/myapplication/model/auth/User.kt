package com.example.myapplication.model.auth

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.util.Date

data class User(
    @Json(name = "id")
    var id: String,

    @Json(name = "email")
    var email: String?,

    @Json(name = "name")
    var name: String?,

    @Json(name = "lastname")
    var lastname: String?,

    @Json(name = "bio")
    var bio: String?,

    @Json(name = "phone")
    var phone: String?,

    @Json(name = "location")
    var location: String?,

    @Json(name = "email_confirmed")
    var emailConfirmed: Boolean?,

    @Json(name = "pass_onboarding")
    val pass_onboarding: Boolean?,

    @Json(name = "experience")
    var experience: Int?,

    @SerializedName("push_token")
    @Json(name = "push_token")
    var pushToken: String?,

    @SerializedName("country_code")
    @Json(name = "country_code")
    var countryCode: String?,

    @SerializedName("avatar_url")
    @Json(name = "avatar_url")
    var avatarUrl: String?,

    @SerializedName("avatar_url_tn")
    @Json(name = "avatar_url_tn")
    var avatarUrlTn: String?,

    @SerializedName("device")
    @Json(name = "device")
    var device: Device?,

    @SerializedName("birthday")
    @Json(name = "birthDay")
    var birthDay: String?,

    @SerializedName("is_muted")
    @Json(name = "is_muted")
    var isMuted: Boolean,

    var lastRefresh: Date?,
    var img: String?,
    var latitude: Double,
    var longitude: Double,
    var userName: String?,
    var tag: String?,
    var rol: String? = null,
    var artist_is_verified: Boolean = false,
    var karma: Int,
    var friends: Int,
    var vibes: Int

)
