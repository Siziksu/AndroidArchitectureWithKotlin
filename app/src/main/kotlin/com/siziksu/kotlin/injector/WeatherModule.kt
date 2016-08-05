package com.siziksu.kotlin.injector

import com.siziksu.kotlin.data.client.weather.IWeatherClient
import com.siziksu.kotlin.data.client.weather.WeatherClient
import com.siziksu.kotlin.data.client.weather.WeatherClientAdapter
import com.siziksu.kotlin.data.weather.GetWeatherData
import com.siziksu.kotlin.data.weather.IGetWeatherData
import com.siziksu.kotlin.domain.weather.GetWeatherRequest
import com.siziksu.kotlin.domain.weather.IGetWeatherRequest
import com.siziksu.kotlin.presenter.weather.IWeatherPresenter
import com.siziksu.kotlin.presenter.weather.IWeatherView
import com.siziksu.kotlin.presenter.weather.WeatherPresenter

object WeatherModule {

    private val getIWeatherClient: IWeatherClient
    private val weatherPresenter: IWeatherPresenter<WeatherPresenter, IWeatherView>

    init {
        weatherPresenter = WeatherPresenter()
        getIWeatherClient = WeatherClient(WeatherClientAdapter.getService())
    }

    val weather: IWeatherPresenter<WeatherPresenter, IWeatherView>
        get() = weatherPresenter.setGetWeatherRequest(weatherRequest)

    private val weatherRequest: IGetWeatherRequest
        get() = GetWeatherRequest(weatherData)

    private val weatherData: IGetWeatherData<*>
        get() = GetWeatherData().setGetWeatherClient(getIWeatherClient)
}
