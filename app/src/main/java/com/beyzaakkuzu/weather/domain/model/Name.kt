package com.beyzaakkuzu.weather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name="match_level")
    val matchLevel:String? = null,

    @Json(name="fullyHighlighted")
    val fullyHighlighted:Boolean?= null,

    @Json(name = "value")
    val value:String?= null,

    @Json(name="matchedWords")
    val matchedWords:List<String?>?= null

)
