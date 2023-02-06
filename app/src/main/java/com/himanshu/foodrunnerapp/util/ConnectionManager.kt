package com.himanshu.foodrunnerapp.util

import android.content.Context
import android.net.ConnectivityManager


@Suppress("DEPRECATION")
open class ConnectionManager{

    fun checkConnectivity(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected ?:false
    }
}