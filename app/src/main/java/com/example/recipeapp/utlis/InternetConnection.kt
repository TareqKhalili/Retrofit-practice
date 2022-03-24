package com.example.recipeapp.utlis

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData

val CONNECTED_TO_INTERNET: MutableLiveData<Boolean> = MutableLiveData()

val networkRequest: NetworkRequest = NetworkRequest.Builder()
    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .build()


val networkCallback = object : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        CONNECTED_TO_INTERNET.postValue(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        CONNECTED_TO_INTERNET.postValue(false)
    }
}
