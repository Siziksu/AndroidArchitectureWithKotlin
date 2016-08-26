package com.siziksu.kotlin.presenter

import android.app.Activity

interface IView {

    val activity: Activity

    fun showProgress(value: Boolean)
}
