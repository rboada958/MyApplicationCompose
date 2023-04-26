package com.example.myapplication.ui.home.bottomTabs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.navigation.AppScreens
import com.example.myapplication.utils.BottomBar
import com.example.myapplication.utils.Toolbar

@Composable
fun CalendarScreen(navController: NavHostController) {
    ContainerCalendar(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerCalendar(navController: NavController) {
    Scaffold(
        topBar = { Toolbar(title = "Calendar") },
        content = { ContentCalendar(navController) },
        bottomBar = { BottomBar(navController) }
    )
}

@Composable
fun ContentCalendar(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center),
            text = "Calendar Screen",
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreenCalendar() {
    val mContext = LocalContext.current
    ContainerCalendar(NavController(mContext))
}
