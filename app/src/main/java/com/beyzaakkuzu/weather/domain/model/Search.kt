package com.beyzaakkuzu.weather.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search(
    @Json(name="hits")
    val hits:List<HitsItem?>?= null
)
