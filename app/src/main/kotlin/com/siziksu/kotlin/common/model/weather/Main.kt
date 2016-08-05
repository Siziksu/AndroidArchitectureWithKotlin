package com.siziksu.kotlin.common.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("temp")
    @Expose
    var temperature: Double? = null
    @SerializedName("pressure")
    @Expose
    var pressure: Double? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Double? = null
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null
}
