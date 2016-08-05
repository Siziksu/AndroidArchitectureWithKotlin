package com.siziksu.kotlin.common.model.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds {

    @SerializedName("all")
    @Expose
    var cloudiness: Int? = null
}
