package com.beyzaakkuzu.weather.domain.data_source.searchCity

import androidx.lifecycle.LiveData
import com.beyzaakkuzu.weather.db.dao.CitiesForSearchDAO
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import com.beyzaakkuzu.weather.domain.model.Search
import javax.inject.Inject

class SearchCitiesLocal @Inject constructor(
    private val searchdao: CitiesForSearchDAO
) {
    fun getCityByName(cityName: String?): LiveData<List<CitiesForSearchEntity>> =
        searchdao.getCityByName(cityName)

    fun insertCities(response: Search) {
        response.hits
            ?.map {
                CitiesForSearchEntity(it)
            }
            ?.let {
                searchdao.insertCities(it)
            }

    }
}