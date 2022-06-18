package com.beyzaakkuzu.weather.domain.data_source.forecast

import com.beyzaakkuzu.weather.domain.WeatherApi
import com.beyzaakkuzu.weather.domain.model.Forecast
import io.reactivex.Single
import javax.inject.Inject

class ForecastRemote @Inject constructor(
    private val api:WeatherApi
){
    fun getForecastByGeoCords(lat:Double,
    lon:Double,units:String): Single<Forecast> = api.getForecastByGeoCords(
        lat, lon, units
    )
}