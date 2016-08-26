package com.siziksu.kotlin.data.weather

import com.siziksu.kotlin.common.model.weather.OpenWeather

interface IGetWeatherData<T : IGetWeatherData<T>> {

    fun city(city: String): T

    fun useCache(): T

    fun cacheExpiryTime(millis: Long): T

    fun run(): OpenWeather
}
