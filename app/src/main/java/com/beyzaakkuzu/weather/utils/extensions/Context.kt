package com.beyzaakkuzu.weather.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


fun isNetworkAvailable(context:Context):Boolean{
    val cm=context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
    val activeNetworkInfo: NetworkInfo? ? = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}