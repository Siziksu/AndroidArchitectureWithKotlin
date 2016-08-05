package com.siziksu.kotlin.domain.weather

import com.siziksu.kotlin.common.model.weather.OpenWeather
import com.siziksu.kotlin.data.weather.IGetWeatherData
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GetWeatherRequest(private val getWeatherData: IGetWeatherData<*>) : IGetWeatherRequest {

    private val EXPIRY_TIME: Long = 5000

    private var city: String = ""

    override fun city(city: String): IGetWeatherRequest {
        this.city = city
        return this
    }

    override fun subscribe(success: (OpenWeather) -> Unit, fail: (Throwable) -> Unit, done: () -> Unit) {
        Observable.create(subscriber())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        { response -> success.invoke(response) },
                        { throwable -> fail.invoke(throwable) },
                        { done.invoke() }
                )
    }

    private fun subscriber(): Observable.OnSubscribe<OpenWeather> {
        return Observable.OnSubscribe<OpenWeather> {
            subscriber ->
            try {
                val result = getWeatherData.city(city).useCache().cacheExpiryTime(EXPIRY_TIME).run()
                if (result.name != null) {
                    subscriber.onNext(result)
                } else {
                    throw Exception("OpenWeather result was null")
                }
                subscriber.onCompleted()
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }
}
