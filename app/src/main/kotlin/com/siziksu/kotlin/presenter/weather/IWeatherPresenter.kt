package com.siziksu.kotlin.presenter.weather

import com.siziksu.kotlin.domain.weather.IGetWeatherRequest
import com.siziksu.kotlin.presenter.Presenter
import com.siziksu.kotlin.presenter.View

abstract class IWeatherPresenter<T : IWeatherPresenter<T, V>, V : View> : Presenter<V>() {

    abstract fun setGetWeatherRequest(getWeatherRequest: IGetWeatherRequest): T

    abstract fun getWeather(city: String)
}
