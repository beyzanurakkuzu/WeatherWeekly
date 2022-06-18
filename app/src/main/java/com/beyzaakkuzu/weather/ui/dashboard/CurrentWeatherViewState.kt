package com.beyzaakkuzu.weather.ui.dashboard


import com.beyzaakkuzu.weather.core.BaseViewState
import com.beyzaakkuzu.weather.db.entity.CurrentWeatherEntity
import com.beyzaakkuzu.weather.utils.domain.Status


class CurrentWeatherViewState(
    val status: Status,
    val error:String?= null,
    val data:CurrentWeatherEntity?=null
): BaseViewState(status,error)