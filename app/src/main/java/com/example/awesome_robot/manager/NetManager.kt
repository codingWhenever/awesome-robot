package com.example.awesome_robot.manager

import android.content.Context
import android.net.ConnectivityManager

class NetManager(var applicationContext: Context) {
    var status: Boolean? = false

    val isConnected2Internet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}