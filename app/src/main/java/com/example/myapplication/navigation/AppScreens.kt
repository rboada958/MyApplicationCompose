package com.example.myapplication.navigation

sealed class AppScreens(val route: String) {
    object ForgotPasswordScreen : AppScreens("forgot_password_screen")
    object LoginScreen : AppScreens("login_screen")
    object HomeScreen : AppScreens("home_screen")
    object PersonScreen : AppScreens("person_screen")
    object SearchScreen : AppScreens("search_screen")
    object CalendarScreen : AppScreens("calendar_screen")
    object SettingScreen : AppScreens("setting_screen")
}
