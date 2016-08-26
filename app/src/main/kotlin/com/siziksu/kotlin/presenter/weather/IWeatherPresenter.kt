package com.siziksu.kotlin.presenter.weather

import com.siziksu.kotlin.presenter.IView

abstract class IWeatherPresenter<V : IView> {

    var view: V? = null

    fun register(view: V) {
        this.view = view
    }

    fun unregister() {
        this.view = null
    }

    abstract fun getWeather(city: String)
}
