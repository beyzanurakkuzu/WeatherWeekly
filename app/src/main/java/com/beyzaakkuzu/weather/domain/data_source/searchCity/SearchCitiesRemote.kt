package com.beyzaakkuzu.weather.domain.data_source.searchCity

import com.algolia.search.saas.places.PlacesClient
import com.squareup.moshi.Moshi
import javax.inject.Inject

class SearchCitiesRemote @Inject constructor(
    private val client: PlacesClient,
    private val moshi: Moshi
) {}