package com.siziksu.kotlin.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import com.siziksu.kotlin.common.model.Connection

object ConnectionUtils {

    private val NOT_CONNECTED = "NOT_CONNECTED"
    private val WIFI = "WIFI"
    private val MOBILE = "MOBILE"
    private val OTHER = "OTHER"

    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    val connection: Connection
        get() {
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo: NetworkInfo? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val networks = connectivityManager.allNetworks
                for (mNetwork in networks) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork)
                    if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                        break
                    }
                }
            } else {
                val networkInfos = connectivityManager.allNetworkInfo
                if (networkInfos != null) {
                    for (info in networkInfos) {
                        if (info.state == NetworkInfo.State.CONNECTED) {
                            networkInfo = info
                            break
                        }
                    }
                }
            }
            if (networkInfo != null) {
                return Connection(true, networkInfo.typeName)
            } else {
                return Connection(false, null)
            }
        }

    val connectionType: String
        get() {
            val connection = connection
            if (connection.isConnected) {
                when (connection.name) {
                    WIFI -> return WIFI
                    MOBILE -> return MOBILE
                    else -> return OTHER
                }
            } else {
                return NOT_CONNECTED
            }
        }

    val ssid: String
        get() {
            val wifiManager = context?.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            return wifiInfo.ssid
        }
}
