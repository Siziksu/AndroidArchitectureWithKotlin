package com.siziksu.kotlin.data.client.weather

import com.siziksu.kotlin.BuildConfig

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClientAdapter {

    const val SERVER = "http://api.openweathermap.org/"
    const val URI = "data/2.5/weather"

    const val WEATHER_API_KEY = "3db09a5921e4199f2cc3c80cc5e07b36"
    const val UNITS = "metric"

    private var service: WeatherClientService? = null

    private val interceptor = HttpLoggingInterceptor()

    fun getService(): WeatherClientService {
        if (service == null) {
            service = builder.addConverterFactory(GsonConverterFactory.create()).build().create(WeatherClientService::class.java)
        }
        return service as WeatherClientService
    }

    private val builder: Retrofit.Builder
        get() {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val builder: Retrofit.Builder
            if (BuildConfig.DEBUG) {
                builder = Retrofit.Builder().baseUrl(SERVER).client(client)
            } else {
                builder = Retrofit.Builder().baseUrl(SERVER)
            }

            return builder
        }
}
