package com.siziksu.kotlin.data.client.weather

import com.siziksu.kotlin.common.model.weather.OpenWeather

interface IWeatherClient {

    fun getWeather(city: String): OpenWeather
}
