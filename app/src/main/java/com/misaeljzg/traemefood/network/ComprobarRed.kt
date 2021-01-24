package com.misaeljzg.traemefood.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ComprobarRed {
    companion object{
        fun hayRed(context: Context)=
                (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                    getNetworkCapabilities(activeNetwork)?.run {
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                                ||hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                                ||hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    }?:false
                }
    }
}