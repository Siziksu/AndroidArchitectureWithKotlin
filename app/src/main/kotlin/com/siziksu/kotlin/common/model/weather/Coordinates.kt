package com.siziksu.kotlin.common.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinates {

    @SerializedName("lon")
    @Expose
    var longitude: Double? = null
    @SerializedName("lat")
    @Expose
    var latitude: Double? = null
}
