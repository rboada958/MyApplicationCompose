package com.example.myapplication.ui.login.domain

import com.example.myapplication.model.auth.LoginResponse
import com.example.myapplication.model.auth.SocialLoginBody
import com.example.myapplication.ui.login.mvvm.LoginRepository
import com.example.myapplication.utils.base.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(username: String, password: String) : Flow<DataState<LoginResponse>> =
         loginRepository.loginUser(username, password)

    suspend fun socialLogin(body: SocialLoginBody) =
        loginRepository.socialLogin(body)

}