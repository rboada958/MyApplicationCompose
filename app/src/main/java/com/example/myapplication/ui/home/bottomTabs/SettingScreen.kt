package com.example.myapplication.ui.home.bottomTabs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.utils.BottomBar
import com.example.myapplication.utils.Toolbar

@Composable
fun SettingScreen(navController: NavHostController) {
    ContainerSetting(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerSetting(navController: NavController) {
    Scaffold(
        topBar = { Toolbar(title = "Settings") },
        content = { ContentSetting(navController) },
        bottomBar = { BottomBar(navController) }
    )
}

@Composable
fun ContentSetting(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center),
            text = "Setting Screen",
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreenSetting() {
    val mContext = LocalContext.current
    ContainerSetting(NavController(mContext))
}