package com.siziksu.kotlin.data.weather

import com.siziksu.kotlin.common.model.weather.OpenWeather
import com.siziksu.kotlin.data.client.weather.IWeatherClient

interface IGetWeatherData<T : IGetWeatherData<T>> {

    fun setGetWeatherClient(client: IWeatherClient): T

    fun city(city: String): T

    fun useCache(): T

    fun cacheExpiryTime(millis: Long): T

    fun run(): OpenWeather
}
