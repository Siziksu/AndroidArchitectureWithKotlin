package com.siziksu.kotlin.presenter.weather

import android.util.Log
import com.siziksu.kotlin.R
import com.siziksu.kotlin.common.Constants
import com.siziksu.kotlin.common.SystemUtils
import com.siziksu.kotlin.common.model.weather.OpenWeather
import com.siziksu.kotlin.common.model.weather.response.WeatherResponse
import com.siziksu.kotlin.domain.weather.IGetWeatherRequest

class WeatherPresenter : IWeatherPresenter<WeatherPresenter, IWeatherView>() {

    private var getWeatherRequestActive: Boolean = false
    private var getWeatherRequest: IGetWeatherRequest? = null

    override fun setGetWeatherRequest(getWeatherRequest: IGetWeatherRequest): WeatherPresenter {
        this.getWeatherRequest = getWeatherRequest
        return this
    }

    override fun getWeather(city: String) {
        if (!getWeatherRequestActive) {
            getWeatherRequestActive = true
            view?.showProgress(true)
            getWeatherRequest?.city(city)?.subscribe(
                    { response -> processGetWeatherResponse(response) },
                    { throwable ->
                        Log.d(Constants.TAG, throwable.message, throwable)
                        view?.onWeatherError()
                        view?.showProgress(false)
                        getWeatherRequestActive = false
                    },
                    {
                        view?.showProgress(false)
                        getWeatherRequestActive = false
                    }
            ) ?: getWeatherRequestIsNull()
        }
    }

    private fun processGetWeatherResponse(response: OpenWeather) {
        val weatherResponse = WeatherResponse()
        weatherResponse.place = response.name
        weatherResponse.temperature = String.format(view?.activity?.getString(R.string.temperature) ?: "", response.main?.temperature)
        weatherResponse.time = String.format(view?.activity?.getString(R.string.temperature_update_time) ?: "", SystemUtils.currentTime)
        view?.onWeather(weatherResponse)
    }

    private fun getWeatherRequestIsNull() {
        view?.showProgress(false)
        getWeatherRequestActive = false
    }
}
