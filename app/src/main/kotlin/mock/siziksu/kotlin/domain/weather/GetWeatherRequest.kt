package mock.siziksu.kotlin.domain.weather

import android.util.Log
import com.google.gson.Gson
import com.siziksu.kotlin.common.Constants
import com.siziksu.kotlin.common.FileUtils
import com.siziksu.kotlin.common.model.weather.OpenWeather
import com.siziksu.kotlin.data.weather.IGetWeatherData
import com.siziksu.kotlin.domain.weather.IGetWeatherRequest

class GetWeatherRequest(private val getWeatherData: IGetWeatherData<*>) : IGetWeatherRequest {

    private val FILE: String = "get_weather/response.json"

    private var city: String = ""

    override fun city(city: String): IGetWeatherRequest {
        this.city = city
        return this
    }

    override fun subscribe(success: (OpenWeather) -> Unit, fail: (Throwable) -> Unit, done: () -> Unit) {
        Log.d(Constants.TAG, "GetWeather mocked response for $city city")
        try {
            val response = FileUtils.getStringFromFile(FILE)
            val openWeather = Gson().fromJson<OpenWeather>(response, OpenWeather::class.java)
            success.invoke(openWeather)
        } catch (e: Exception) {
            fail.invoke(Throwable("GetWeather mocked error"))
        }
        done.invoke()
    }
}
