package com.siziksu.kotlin.data.client.weather

import android.util.Log

import com.siziksu.kotlin.common.Constants
import com.siziksu.kotlin.common.model.weather.OpenWeather

import java.io.IOException

class WeatherClient(private val service: WeatherClientService) : IWeatherClient {

    override fun getWeather(city: String): OpenWeather {
        try {
            val call = service.getWeather(city, WeatherClientAdapter.WEATHER_API_KEY, WeatherClientAdapter.UNITS)
            val openWeather = call.execute().body()
            if (openWeather != null) {
                return openWeather
            } else {
                throw RuntimeException("Retrofit call returned a null value")
            }
        } catch (e: IOException) {
            Log.d(Constants.TAG, e.message, e)
        }

        throw RuntimeException("Unknown error for the retrofit call")
    }
}
