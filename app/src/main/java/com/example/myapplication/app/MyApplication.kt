package com.example.myapplication.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var applicationInstance: MyApplication

        val appContext: Context by lazy {
            applicationInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationInstance = this
    }
}