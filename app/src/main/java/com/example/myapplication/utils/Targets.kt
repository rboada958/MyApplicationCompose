package com.example.myapplication.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.navigation.AppScreens
import com.example.myapplication.ui.home.tabs.ItemTabs
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState

@ExperimentalMaterial3Api
@Composable
fun Dialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        containerColor = Color(0xFFF44336),
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(fontSize = 14.sp, text = "Accept")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(fontSize = 14.sp, text = "Cancel")
            }
        },
        title = { Text(text = "What is Lorem Ipsum?") },
        text = { Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry") }
    )
}

@ExperimentalMaterial3Api
@Composable
fun Toolbar(title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(fontSize = 18.sp, text = title)
            }
        },
        modifier = Modifier.height(48.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFFF44336),
            titleContentColor = Color.Black
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar(
        contentColor = Color.Black,
        containerColor = Color(0xFFF44336),
        modifier = Modifier.height(48.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = { navController.navigate(AppScreens.HomeScreen.route) }) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate(AppScreens.PersonScreen.route) }) {
                Icon(Icons.Filled.Person, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate(AppScreens.SearchScreen.route) }) {
                Icon(Icons.Filled.Search, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate(AppScreens.CalendarScreen.route) }) {
                Icon(Icons.Filled.DateRange, contentDescription = null)
            }
            IconButton(onClick = { navController.navigate(AppScreens.SettingScreen.route) }) {
                Icon(Icons.Filled.Settings, contentDescription = null)
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabLayout(tabs: List<ItemTabs>) {
    val pagerState = rememberPagerState()

    Column {
        Tabs(tabs, pagerState)
        TabsContent(tabs, pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<ItemTabs>, pagerState: PagerState) {
    HorizontalPager(
        count = tabs.size,
        state = pagerState
    ) { page ->
        tabs[page].screen()
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<ItemTabs>, pagerState: PagerState) {

    TabRow(
        backgroundColor = Color(0xFFF44336),
        contentColor = Color.Black,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabsPosition ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabsPosition)
            )
        }
    ) {
        tabs.forEachIndexed { index, item ->
            val context = LocalContext.current
            Tab(
                onClick = { mToast(context, "Tab $index") },
                selected = pagerState.currentPage == index,
                text = { Text(text = item.title) }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar(onSearch: ((String) -> Unit)) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it; onSearch(it) },
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(Icons.Filled.Search, "") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = Color(0xFFF44336),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}
