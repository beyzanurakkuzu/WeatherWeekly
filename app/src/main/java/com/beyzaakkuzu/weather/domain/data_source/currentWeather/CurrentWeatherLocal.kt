package com.beyzaakkuzu.weather.domain.data_source.currentWeather

import com.beyzaakkuzu.weather.db.dao.CurrentWeatherDAO
import com.beyzaakkuzu.weather.db.entity.CurrentWeatherEntity
import com.beyzaakkuzu.weather.domain.model.CurrentWeatherResponse
import javax.inject.Inject

class CurrentWeatherLocal @Inject constructor(
    private val dao:CurrentWeatherDAO
){

    fun getCurrentWeather()= dao.getCurrentWeather()
    fun insertCurrentWeather(cws:CurrentWeatherResponse)= dao.deleteAndInsert(
        CurrentWeatherEntity(cws)
    )
}