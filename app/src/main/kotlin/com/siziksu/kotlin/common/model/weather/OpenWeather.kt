package com.siziksu.kotlin.common.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class OpenWeather {

    @SerializedName("coord")
    @Expose
    var coordinates: Coordinates? = null
    @SerializedName("weather")
    @Expose
    var weather: List<Weather> = ArrayList()
    @SerializedName("base")
    @Expose
    var base: String? = null
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    @SerializedName("dt")
    @Expose
    var dataTime: Long? = null
    @SerializedName("sys")
    @Expose
    var system: System? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("cod")
    @Expose
    var cod: Int? = null
}
