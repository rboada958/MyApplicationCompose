package com.example.myapplication.model.auth

import com.google.gson.annotations.SerializedName

data class SocialLoginBody(
    @SerializedName("id_social_login") val iSocialLogin: String,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("type_social_login") val typeSocialLogin: String
)
