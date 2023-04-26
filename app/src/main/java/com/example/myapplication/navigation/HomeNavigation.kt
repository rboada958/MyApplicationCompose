package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.forgotPassword.ForgotPasswordScreen
import com.example.myapplication.ui.home.HomeScreen
import com.example.myapplication.ui.home.bottomTabs.CalendarScreen
import com.example.myapplication.ui.home.bottomTabs.PersonScreen
import com.example.myapplication.ui.home.bottomTabs.SearchScreen
import com.example.myapplication.ui.home.bottomTabs.SettingScreen
import com.example.myapplication.ui.login.LoginScreen

@Composable
fun HomeNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) { HomeScreen(navController) }
        composable(route = AppScreens.PersonScreen.route) { PersonScreen(navController) }
        composable(route = AppScreens.SearchScreen.route) { SearchScreen(navController) }
        composable(route = AppScreens.CalendarScreen.route) { CalendarScreen(navController) }
        composable(route = AppScreens.SettingScreen.route) { SettingScreen(navController) }
    }
}