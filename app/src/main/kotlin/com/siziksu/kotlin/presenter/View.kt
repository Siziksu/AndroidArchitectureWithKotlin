package com.siziksu.kotlin.presenter

import android.app.Activity

interface View {

    val activity: Activity

    fun showProgress(value: Boolean)
}
