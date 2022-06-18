package com.beyzaakkuzu.weather.repository

import androidx.lifecycle.LiveData
import com.beyzaakkuzu.weather.db.entity.ForecastEntity
import com.beyzaakkuzu.weather.domain.data_source.forecast.ForecastLocal
import com.beyzaakkuzu.weather.domain.data_source.forecast.ForecastRemote
import com.beyzaakkuzu.weather.domain.model.Forecast
import com.beyzaakkuzu.weather.other.Constants.NetworkService.RATE_LIMITER_TYPE
import com.beyzaakkuzu.weather.utils.domain.RateLimiter
import com.beyzaakkuzu.weather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val fr:ForecastRemote,
    private val fl:ForecastLocal
){
    private val forecastListRateLimit=  RateLimiter<String>(30,TimeUnit.SECONDS)

    fun loadForecastByCoord(lat:Double,lon:Double,fetchRequired:Boolean,units:String): LiveData<Resource<ForecastEntity>> {
         return object : NetworkBoundResource<ForecastEntity, Forecast>(){
             override fun saveCallResult(item: Forecast)=fl.insertForecast(item)
             override fun shouldFetch(data: ForecastEntity): Boolean =fetchRequired
             override fun loadFromDb(): LiveData<ForecastEntity> = fl.getForecast()
             override fun createCall(): Single<Forecast> = fr.getForecastByGeoCords(
                 lat, lon, units
             )

             override fun onFetchFailed() {
                 forecastListRateLimit.reset(RATE_LIMITER_TYPE)
             }
         }.asLiveData
    }
}