package com.beyzaakkuzu.weather.domain.data_source.forecast

import com.beyzaakkuzu.weather.db.dao.ForecastDAO
import com.beyzaakkuzu.weather.db.entity.ForecastEntity
import com.beyzaakkuzu.weather.domain.model.Forecast
import javax.inject.Inject

class ForecastLocal @Inject constructor(
    private  val fd:ForecastDAO
){
    fun getForecast()= fd.getForecast()
    fun insertForecast(fs:Forecast)= fd.deleteAndInsert(
        ForecastEntity(fs)
    )
}