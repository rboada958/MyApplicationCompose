package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myapplication.navigation.AuthNavigation
import com.example.myapplication.ui.login.LoginScreen
import com.example.myapplication.ui.login.mvvm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<LoginViewModel>()
        setContent {
            AuthNavigation(viewModel)
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun PreviewScreen() {
        val viewModel by viewModels<LoginViewModel>()
        val context = LocalContext.current
        LoginScreen(
            navController = NavController(context),
            viewModel = viewModel
        )
    }
}
