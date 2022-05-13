package com.beyzaakkuzu.weather.domain.data_source.currentWeather

import com.beyzaakkuzu.weather.domain.WeatherApi
import com.beyzaakkuzu.weather.domain.model.CurrentWeatherResponse
import javax.inject.Inject
import io.reactivex.Single

class CurrentWeatherRemote @Inject constructor(
    private val api:WeatherApi
) {
    fun getCurrentWeatherByGeoCords(lat:Double,lon:Double,units:String):Single<CurrentWeatherResponse> = api.getCurrentWeatherByGeoCords(
        lat,lon,units
    )
}








