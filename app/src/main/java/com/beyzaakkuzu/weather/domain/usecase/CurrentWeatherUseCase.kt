package com.beyzaakkuzu.weather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.beyzaakkuzu.weather.db.entity.CurrentWeatherEntity
import com.beyzaakkuzu.weather.other.Constants
import com.beyzaakkuzu.weather.repository.CurrentWeatherRepository
import com.beyzaakkuzu.weather.ui.dashboard.CurrentWeatherViewState
import com.beyzaakkuzu.weather.utils.UseCaseLiveData
import com.beyzaakkuzu.weather.utils.domain.Resource
import javax.inject.Inject

class CurrentWeatherUseCase @Inject internal constructor(
    private val repo:CurrentWeatherRepository
):UseCaseLiveData<CurrentWeatherViewState, CurrentWeatherUseCase.CurrentWeatherParams, CurrentWeatherRepository>() {

    override fun getRepository(): CurrentWeatherRepository{
        return repo
    }

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<CurrentWeatherViewState> {
        return repo.loadCurrentWeatherByGeoCords(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
        units = params?.units?: Constants.Coords.METRIC).map{
            onCurrentWeatherResultReady(it)
        }
    }

    private fun onCurrentWeatherResultReady(resource:Resource<CurrentWeatherEntity>):CurrentWeatherViewState{
        return CurrentWeatherViewState(
            status = resource.status,
            error= resource.message,
            data = resource.data
        )
    }

    class CurrentWeatherParams(
        val lat:String="",
        val lon:String="",
        val fetchRequired: Boolean,
        val units: String):Params()
}