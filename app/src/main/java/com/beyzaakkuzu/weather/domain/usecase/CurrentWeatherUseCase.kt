package com.beyzaakkuzu.weather.domain.usecase

import androidx.lifecycle.LiveData
import com.beyzaakkuzu.weather.repository.CurrentWeatherRepository
import com.beyzaakkuzu.weather.ui.dashboard.CurrentWeatherViewState
import com.beyzaakkuzu.weather.utils.UseCaseLiveData
import javax.inject.Inject

class CurrentWeatherUseCase @Inject internal constructor(
    private val repo:CurrentWeatherRepository
):UseCaseLiveData<CurrentWeatherViewState, CurrentWeatherUseCase.CurrentWeatherParams, CurrentWeatherRepository>() {

    override fun getRepository(): CurrentWeatherRepository{
        return repo
    }

    class CurrentWeatherParams(val lat:String="",
    val lon:String?="",val fetchRequired:Boolean,
    val units:String):Params()

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<CurrentWeatherViewState> {

    }
}