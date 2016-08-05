package com.siziksu.kotlin.presenter.weather

import com.siziksu.kotlin.common.model.weather.response.WeatherResponse
import com.siziksu.kotlin.presenter.View

interface IWeatherView : View {

    fun onWeather(weatherResponse: WeatherResponse)

    fun onWeatherError()
}
