package com.siziksu.kotlin.common.extensions

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

fun AppCompatActivity.setToolbar(toolbar: Toolbar) {
    this.setSupportActionBar(toolbar)
}

fun AppCompatActivity.applyToolBarStyleWithHome(toolbar: Toolbar) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayShowHomeEnabled(true)
}
