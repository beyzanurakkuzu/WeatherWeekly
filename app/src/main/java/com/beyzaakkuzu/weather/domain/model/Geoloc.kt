package com.beyzaakkuzu.weather.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Geoloc(
    @Json(name="lng")
    val lng:Double?= null,
    @Json(name="lat")
    val lat:Double?= null
):Parcelable
