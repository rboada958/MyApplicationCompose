package com.example.myapplication.utils

import android.content.Context
import android.widget.Toast
import com.example.myapplication.app.MyApplication

fun mToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun getString(string : Int) : String {
    return MyApplication.appContext.getString(string)
}