package com.beyzaakkuzu.weather.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ListItem(
    @Json(name="dt")
    val dt:Long?,
    @Json(name="rain")
    val rain:Rain?,

    @Json(name="dt_txt")
    val dtTxt:String?,
    @Json(name="snow")
    val snow: Snow,
    @Json(name="weather")
    val weather: List<Weather?>?,
    @Json(name="clouds")
    val clouds: Clouds,

    @Json(name="sys")
    val sys: Sys,
    @Json(name="dt")
    val wind: Wind,
    @Json(name="main")
    val main: Main
): Parcelable {

}

