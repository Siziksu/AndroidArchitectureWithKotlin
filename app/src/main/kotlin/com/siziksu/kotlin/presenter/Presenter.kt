package com.siziksu.kotlin.presenter

abstract class Presenter<T : View> {

    protected var view: T? = null

    fun register(view: T) {
        this.view = view
    }

    fun unregister() {
        this.view = null
    }
}
