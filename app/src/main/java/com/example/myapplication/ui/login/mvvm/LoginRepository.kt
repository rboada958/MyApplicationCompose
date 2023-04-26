package com.example.myapplication.ui.login.mvvm

import com.example.myapplication.BuildConfig
import com.example.myapplication.app.data.LocalDataStore
import com.example.myapplication.app.networks.api.AuthApi
import com.example.myapplication.model.auth.AppVersionInfo
import com.example.myapplication.model.auth.LoginParams
import com.example.myapplication.model.auth.LoginResponse
import com.example.myapplication.model.auth.SignUpParams
import com.example.myapplication.model.auth.SocialLoginBody
import com.example.myapplication.model.auth.User
import com.example.myapplication.utils.base.BaseRemoteRepository
import com.example.myapplication.utils.base.DataState
import com.example.myapplication.utils.base.RemoteErrorEmitter
import com.example.myapplication.utils.base.runApiCall
import com.example.myapplication.utils.base.runRemoteApiCall
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepository(
    private val localDataStore: LocalDataStore,
    private val authApi: AuthApi,
) : BaseRemoteRepository() {

    suspend fun loginUser(username: String, password: String) =
        flow {
            runRemoteApiCall(
                success = {
                    emit(DataState.Success(data.data))
                }
            ) {
                authApi.loginUser(LoginParams(username, password))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun socialLogin(body: SocialLoginBody) =
        flow {
            runRemoteApiCall(
                success = {
                    emit(DataState.Success(data.data))
                }
            ) {
                authApi.socialLogin(body)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun sendDeviceInfo() =
        flow {
            emit(
                runApiCall {
                    val osVersion =
                        "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL} ${android.os.Build.VERSION.RELEASE} SDK ${android.os.Build.VERSION.SDK_INT}"
                    val appVersion =
                        "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) ${BuildConfig.BUILD_TYPE}"

                    val appInfo = AppVersionInfo(
                        "android",
                        osVersion,
                        appVersion
                    )

                    authApi.saveAppInfo(appInfo)
                }
            )
        }

    suspend fun storeUserSession(loginResponse: LoginResponse) {
        loginResponse.accessToken?.let { localDataStore.setAccessToken(it) }
        localDataStore.setUserData(Gson().toJson(loginResponse.user))
    }

    suspend fun storeSessionUser(user: User) {
        localDataStore.setUserData(Gson().toJson(user))
    }

    fun getSessionUser() = localDataStore.getUserData()

    suspend fun signUpUser(username: String, password: String, forNewslatter: Boolean) =
        flow {
            runRemoteApiCall(
                success = {
                    emit(DataState.Success(data.data))
                }
            ) {
                authApi.signUpUser(SignUpParams(username, password, forNewslatter))
            }
        }.flowOn(Dispatchers.IO)

    fun getAccessToken() = localDataStore.getAuthToken()

    fun getLoginCredentials() = localDataStore.getLoginCredentials()

    suspend fun storeUserCredentials(loginCredentials: LoginParams) {
        localDataStore.setUserCredentials(loginCredentials)
    }

    suspend fun recoverPassword(email: String, emitter: RemoteErrorEmitter) =
        safeApiCall(emitter) { authApi.recoverPassword(email) }

}