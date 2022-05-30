package com.beyzaakkuzu.weather.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.beyzaakkuzu.weather.core.BaseViewModel
import com.beyzaakkuzu.weather.domain.usecase.CurrentWeatherUseCase
import com.beyzaakkuzu.weather.domain.usecase.ForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject internal constructor(
    private val forecastUseCase: ForecastUseCase,
    private val currentWeatherUseCase: CurrentWeatherUseCase,
    var sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val forecastParams: MutableLiveData<ForecastUseCase.ForecastParams> = MutableLiveData()
    private val currentWeatherParams: MutableLiveData<CurrentWeatherUseCase.CurrentWeatherParams> =
        MutableLiveData()

    fun getForecastViewState() = forecastViewState
    fun getCurrentWeatherViewState() = currentWeatherViewState

    private val forecastViewState: LiveData<ForecastViewState> =
        forecastParams.switchMap { params ->
            forecastUseCase.execute(params)
        }
    private val currentWeatherViewState: LiveData<CurrentWeatherViewState> =
        currentWeatherParams.switchMap { params ->
            currentWeatherUseCase.execute(params)
        }

    fun setForecastParams(params: ForecastUseCase.ForecastParams) {
        if (forecastParams.value == params
        ) {
            return
        }
        forecastParams.postValue(params)

    }

    fun setCurrentWeatherParams(params: CurrentWeatherUseCase.CurrentWeatherParams) {
        if (currentWeatherParams.value == params) {
            return
        }
        currentWeatherParams.postValue(
            params
        )
    }
}