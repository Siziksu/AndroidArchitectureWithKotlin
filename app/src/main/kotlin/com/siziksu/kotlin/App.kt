package com.siziksu.kotlin

import android.app.Application
import com.siziksu.kotlin.common.ConnectionUtils
import com.siziksu.kotlin.common.FileUtils
import com.siziksu.kotlin.common.Preferences

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ConnectionUtils.init(applicationContext)
        Preferences.init(applicationContext)
        FileUtils.init(applicationContext)
    }
}

