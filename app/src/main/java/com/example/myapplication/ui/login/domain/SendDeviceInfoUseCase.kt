package com.example.myapplication.ui.login.domain

import com.example.myapplication.ui.login.mvvm.LoginRepository
import javax.inject.Inject

class SendDeviceInfoUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke() =
        loginRepository.sendDeviceInfo()
}