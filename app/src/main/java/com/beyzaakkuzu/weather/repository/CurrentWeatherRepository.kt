package com.beyzaakkuzu.weather.repository

import androidx.lifecycle.LiveData
import com.beyzaakkuzu.weather.db.entity.CurrentWeatherEntity
import com.beyzaakkuzu.weather.domain.data_source.currentWeather.CurrentWeatherLocal
import com.beyzaakkuzu.weather.domain.data_source.currentWeather.CurrentWeatherRemote
import com.beyzaakkuzu.weather.domain.model.CurrentWeatherResponse
import com.beyzaakkuzu.weather.other.Constants.NetworkService.RATE_LIMITER_TYPE
import com.beyzaakkuzu.weather.utils.domain.RateLimiter
import com.beyzaakkuzu.weather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrentWeatherRepository @Inject constructor(
    private val cwr: CurrentWeatherRemote,
    private val cwl: CurrentWeatherLocal
) {

    private val currentWeatherRateLimit =
        RateLimiter<String>(30, TimeUnit.SECONDS) //mevcut hava durumu oran limiti

    fun loadCurrentWeatherByGeoCords(
        lat: Double,
        lon: Double,
        fetchRequired: Boolean,
        units: String
    ): LiveData<Resource<CurrentWeatherEntity>> {
        return object : NetworkBoundResource<CurrentWeatherEntity, CurrentWeatherResponse>() {
            override fun saveCallResult(item: CurrentWeatherResponse) =
                cwl.insertCurrentWeather(item)

            override fun loadFromDb(): LiveData<CurrentWeatherEntity> = cwl.getCurrentWeather()
            override fun createCall(): Single<CurrentWeatherResponse> = cwr.getCurrentWeatherByGeoCords(
                lat,
                lon,
                units
            )
            override fun onFetchFailed() = currentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
            override fun shouldFetch(data: CurrentWeatherEntity): Boolean = fetchRequired


        }.asLiveData
    }
}