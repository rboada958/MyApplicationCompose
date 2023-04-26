package com.example.myapplication.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.myapplication.navigation.HomeNavigation

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeNavigation()
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun PreviewScreen() {
        val context = LocalContext.current
        HomeScreen(NavController(context))
    }
}