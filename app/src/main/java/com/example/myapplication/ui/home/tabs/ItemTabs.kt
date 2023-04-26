package com.example.myapplication.ui.home.tabs

import androidx.compose.runtime.Composable

typealias MyFun = @Composable () -> Unit

sealed class ItemTabs(
    var title: String,
    var screen: MyFun
) {
    object ItemDogs : ItemTabs("Dogs", { Dogs() })
    object ItemCats : ItemTabs("Cats", { Cats() })
    object ItemRabbit : ItemTabs("Rabbits", { Rabbits() })
    object ItemOthers : ItemTabs("Others", { Others() })
}
