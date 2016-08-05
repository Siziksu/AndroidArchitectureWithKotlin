package com.siziksu.kotlin.data.client.weather

import com.siziksu.kotlin.common.model.weather.OpenWeather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherClientService {

    @GET(WeatherClientAdapter.URI)
    fun getWeather(@Query("q") city: String?, @Query("apikey") apiKey: String, @Query("units") units: String): Call<OpenWeather>
}
