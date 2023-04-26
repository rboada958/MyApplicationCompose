package com.example.myapplication.app.networks.interceptor

import com.example.myapplication.R
import com.example.myapplication.utils.getString
import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = getString(R.string.connectivity_exception)
}
