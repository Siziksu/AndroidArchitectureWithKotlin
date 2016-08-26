package com.siziksu.kotlin.data.weather

import com.google.gson.Gson
import com.siziksu.kotlin.common.model.weather.OpenWeather
import com.siziksu.kotlin.data.Data
import com.siziksu.kotlin.data.client.weather.IWeatherClient

class GetWeatherData(private val weatherClient: IWeatherClient) : Data(), IGetWeatherData<GetWeatherData> {

    private val KEY_WEATHER_CACHE = "weather_cache"
    private val EXPIRY_TIME: Long = 30000

    private var city: String = ""
    private var useCache: Boolean = false
    private var expiry: Long = 0

    override fun city(city: String): GetWeatherData {
        this.city = city
        return this
    }

    override fun useCache(): GetWeatherData {
        this.useCache = true
        return this
    }

    override fun cacheExpiryTime(millis: Long): GetWeatherData {
        this.expiry = millis
        return this
    }

    override fun run(): OpenWeather {
        if (!useCache) {
            return getWeatherFromClient() ?: OpenWeather()
        }
        return getOpenWeatherFromCache() ?: getWeatherFromClient() ?: OpenWeather()
    }

    private fun getWeatherFromClient(): OpenWeather? {
        val openWeather = weatherClient.getWeather(city)
        if (useCache) {
            val newCache = Gson().toJson(openWeather)
            setCache(KEY_WEATHER_CACHE, newCache)
        }
        return openWeather
    }

    private fun getOpenWeatherFromCache(): OpenWeather? {
        val cache = getCache(KEY_WEATHER_CACHE)
        val expiryTime = if (expiry > 0) expiry else EXPIRY_TIME
        if (isCacheValid(cache, KEY_WEATHER_CACHE, expiryTime)) {
            return Gson().fromJson(cache, OpenWeather::class.java)
        }
        return null
    }
}
