package com.beyzaakkuzu.weather.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Country (
    @Json(name="matchLevel")
    val matchLevel:String? = null,
    @Json(name="value")
    val value:String?= null,
    @Json(name = "matchedWords")
    val matchedWords: List<Any?>? = null

)