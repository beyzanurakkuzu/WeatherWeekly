package com.beyzaakkuzu.weather.repository

import androidx.lifecycle.LiveData
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import com.beyzaakkuzu.weather.domain.data_source.searchCity.SearchCitiesLocal
import com.beyzaakkuzu.weather.domain.data_source.searchCity.SearchCitiesRemote
import com.beyzaakkuzu.weather.domain.model.Search
import com.beyzaakkuzu.weather.other.Constants.NetworkService.RATE_LIMITER_TYPE
import com.beyzaakkuzu.weather.utils.domain.RateLimiter
import com.beyzaakkuzu.weather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchCitiesRepository @Inject constructor(
    private val sr: SearchCitiesRemote,
    private val sl: SearchCitiesLocal
) {

    private val rateLimiter = RateLimiter<String>(1, TimeUnit.SECONDS)
    fun loadCitiesByCityName(cityName: String?): LiveData<Resource<List<CitiesForSearchEntity>>> {
        return object : NetworkBoundResource<List<CitiesForSearchEntity>, Search>() {

            override fun shouldFetch(data: List<CitiesForSearchEntity>): Boolean {
                return data.isEmpty()
            }

            override fun saveCallResult(item: Search) = sl.insertCities(item)

            override fun loadFromDb(): LiveData<List<CitiesForSearchEntity>> =
                sl.getCityByName(cityName)

            override fun onFetchFailed() = rateLimiter.reset(RATE_LIMITER_TYPE)
            override fun createCall(): Single<Search> = sr.getCityWithQuery(
                cityName
                    ?: ""
            )
        }.asLiveData
    }
}