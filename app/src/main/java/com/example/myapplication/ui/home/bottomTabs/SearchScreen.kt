package com.example.myapplication.ui.home.bottomTabs

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.myapplication.utils.BottomBar
import com.example.myapplication.utils.SearchBar

@Composable
fun SearchScreen(navController: NavHostController) {
    ContainerSearch(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerSearch(navController: NavHostController) {
    Scaffold(
        content = { ContentSearch() },
        bottomBar = { BottomBar(navController) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun ContentSearch() {
    var searchQuery by remember { mutableStateOf("") }
    SearchBar(onSearch = { query -> searchQuery = query })
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreenSearch() {
    val mContext = LocalContext.current
    ContainerSearch(NavHostController(mContext))
}