package com.beyzaakkuzu.weather.domain

import com.beyzaakkuzu.weather.domain.model.CurrentWeatherResponse
import com.beyzaakkuzu.weather.domain.model.Forecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    fun getForecastByGeoCords(
        @Query("lat")
        lat:Double,
        @Query("lon")
        lon:Double,
        @Query("units")
        units:String
    ):Single<Forecast>

    @GET("weather")
    fun getCurrentByGeoCords(
        @Query("lat")
        lat:Double,
        @Query("lon")
        lon:Double,
        @Query("units")
        units:String
    ):Single<CurrentWeatherResponse>
}