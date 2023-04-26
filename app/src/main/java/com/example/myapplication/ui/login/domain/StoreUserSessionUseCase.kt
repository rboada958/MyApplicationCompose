package com.example.myapplication.ui.login.domain

import com.example.myapplication.model.auth.LoginParams
import com.example.myapplication.model.auth.LoginResponse
import com.example.myapplication.ui.login.mvvm.LoginRepository
import javax.inject.Inject

class StoreUserSessionUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun storeUserSession(session: LoginResponse) =
        loginRepository.storeUserSession(session)

    suspend fun storeUserCredentials(credentials: LoginParams) =
        loginRepository.storeUserCredentials(credentials)
}