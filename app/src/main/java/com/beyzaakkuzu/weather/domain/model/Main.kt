package com.beyzaakkuzu.weather.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter=true)
data class Main(
    @Json(name="temp")
    val temp:Double?,
    @Json(name="feels_like")
    val feels_like:Double?,
    @Json(name="temp_min")
    val tempMin:Double?,
    @Json(name="temp_max")
    val tempMax:Double?,
    @Json(name="pressure")
    val pressure:Double?,
    @Json(name="humidity")
    val humidity:Double?
):Parcelable{


    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }


    fun getTempMinString(): String {
        return tempMin.toString().substringBefore(".") + "°"
    }

    fun getTempMaxString(): String {
        return tempMax.toString().substringBefore(".") + "°"
    }

}
