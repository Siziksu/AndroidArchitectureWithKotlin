package com.siziksu.kotlin.domain.weather

import com.siziksu.kotlin.common.model.weather.OpenWeather

interface IGetWeatherRequest {

    fun city(city: String): IGetWeatherRequest

    fun subscribe(success: (OpenWeather) -> Unit, fail: (Throwable) -> Unit, done: () -> Unit)
}
