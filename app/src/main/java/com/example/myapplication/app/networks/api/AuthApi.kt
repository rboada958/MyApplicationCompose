package com.example.myapplication.app.networks.api

import com.example.myapplication.model.BaseResponse
import com.example.myapplication.model.auth.AppVersionInfo
import com.example.myapplication.model.auth.LoginParams
import com.example.myapplication.model.auth.LoginResponse
import com.example.myapplication.model.auth.RecoverPasswordResponse
import com.example.myapplication.model.auth.SignUpParams
import com.example.myapplication.model.auth.SimpleStatusResponse
import com.example.myapplication.model.auth.SocialLoginBody
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @POST("movil/auth/login")
    suspend fun loginUser(@Body loginParams: LoginParams): ApiResponse<BaseResponse<LoginResponse>>

    @POST("movil/auth/social_login")
    suspend fun socialLogin(@Body body: SocialLoginBody): ApiResponse<BaseResponse<LoginResponse>>

    @PUT("movil/users/device")
    suspend fun saveAppInfo(@Body appVersionInfo: AppVersionInfo): Response<SimpleStatusResponse>

    @POST("movil/auth/register")
    suspend fun signUpUser(@Body signUpParams: SignUpParams): ApiResponse<BaseResponse<LoginResponse>>

    @FormUrlEncoded
    @POST("movil/auth/reset_password")
    suspend fun recoverPassword(@Field("email") email: String): RecoverPasswordResponse

}