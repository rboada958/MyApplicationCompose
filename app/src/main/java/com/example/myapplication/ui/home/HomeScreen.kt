package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.home.tabs.ItemTabs
import com.example.myapplication.utils.BottomBar
import com.example.myapplication.utils.Dialog
import com.example.myapplication.utils.TabLayout
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    ViewContainer(navController)
}

@ExperimentalPagerApi
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun ViewContainer(navController: NavController) {
    Scaffold(
        content = { Content() },
        floatingActionButton = { Fab() },
        bottomBar = { BottomBar(navController) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun Fab() {
    var isAlertDialogOpen by remember { mutableStateOf(false) }

    FloatingActionButton(
        containerColor = Color(0xFFF44336),
        onClick = { isAlertDialogOpen = true }) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = null
        )
    }

    if (isAlertDialogOpen) {
        Dialog(
            onDismiss = { isAlertDialogOpen = false },
            onConfirm = { isAlertDialogOpen = false }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
fun Content() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        TabLayout(
            tabs = listOf(
                ItemTabs.ItemDogs,
                ItemTabs.ItemCats,
                ItemTabs.ItemRabbit,
                ItemTabs.ItemOthers
            )
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewScreen() {
    val mContext = LocalContext.current
    ViewContainer(navController = NavController(mContext))
}