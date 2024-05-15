package com.jeju.nanaland.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkManager @Inject constructor(
    private val context: Context
){
    val isNetworkConnected: Boolean
        get() {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(ConnectivityManager::class.java)

        val network: Network = connectivityManager.activeNetwork ?: return false
        val actNetwork: NetworkCapabilities =
            connectivityManager.getNetworkCapabilities(network)?: return false

        return when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }
}