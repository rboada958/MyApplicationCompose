package com.example.myapplication.ui.login.mvvm

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.login.domain.LogInUseCase
import com.example.myapplication.ui.login.domain.SendDeviceInfoUseCase
import com.example.myapplication.ui.login.domain.StoreUserSessionUseCase
import com.example.myapplication.utils.base.DataState
import com.example.myapplication.utils.base.collectAndRun
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val storeUserSessionUseCase: StoreUserSessionUseCase,
    private val sendDeviceInfoUseCase: SendDeviceInfoUseCase
) : ViewModel() {

    private val emailMutableLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = emailMutableLiveData

    private val passwordMutableLiveData = MutableLiveData<String>()
    val passwordLiveData: LiveData<String> = passwordMutableLiveData

    private val loginEnable = MutableLiveData<Boolean>()
    val loginEnableLiveData: LiveData<Boolean> = loginEnable

    private val isLoading = MutableLiveData<Boolean>()
    val loadingEnableLiveData: LiveData<Boolean> = isLoading

    private val success = MutableLiveData<Boolean>()
    val successEnableLiveData: LiveData<Boolean> = success

    fun onLoginChange(email: String, password: String) {
        emailMutableLiveData.value = email
        passwordMutableLiveData.value = password
        loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String) = password.length > 6

    fun onLoginSelected(email: String, password: String) =
        viewModelScope.launch {
            isLoading.value = true
            logInUseCase(username = email, password = password).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        storeUserSessionUseCase.storeUserSession(dataState.data)
                        sendDeviceInfoUseCase().collectAndRun {
                            success.value = true
                            isLoading.value = false
                        }
                    }

                    is DataState.OtherError -> {
                        isLoading.value = false
                    }

                    else -> {}
                }
            }
        }
}