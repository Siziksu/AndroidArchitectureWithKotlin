package com.siziksu.kotlin.presenter.weather

import com.siziksu.kotlin.common.model.weather.response.WeatherResponse
import com.siziksu.kotlin.presenter.IView

interface IWeatherView : IView {

    fun onWeather(weatherResponse: WeatherResponse)

    fun onWeatherError()
}
