package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.forgotPassword.ForgotPasswordScreen
import com.example.myapplication.ui.login.LoginScreen
import com.example.myapplication.ui.login.mvvm.LoginViewModel

@Composable
fun AuthNavigation(viewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route) { LoginScreen(navController, viewModel) }
        composable(route = AppScreens.ForgotPasswordScreen.route) { ForgotPasswordScreen(navController, viewModel) }
    }
}