package com.siziksu.kotlin.common

import android.os.Bundle
import android.util.Log
import java.util.*

object SystemUtils {

    val currentTime: String
        get() {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = calendar.get(Calendar.MINUTE)
            val seconds = calendar.get(Calendar.SECOND)
            if (hour < 10) {
                val result = "0$hour:" + (if (minutes < 10) "0$minutes:" else "$minutes:") + if (seconds < 10) "0$seconds" else seconds
                return result
            } else {
                val result = "$hour:" + (if (minutes < 10) "0$minutes:" else "$minutes:") + if (seconds < 10) "0$seconds" else seconds
                return result
            }
        }

    fun printBundle(bundle: Bundle) {
        for (key in bundle.keySet()) {
            Log.d(Constants.TAG, key + ": " + bundle.get(key))
        }
    }

    fun pause(milliseconds: Long) {
        try {
            Thread.sleep(milliseconds)
        } catch (e: InterruptedException) {
            Log.d(Constants.TAG, e.message, e)
        }

    }
}
