package com.beyzaakkuzu.weather.ui.weather_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyzaakkuzu.weather.core.BaseViewModel
import com.beyzaakkuzu.weather.db.entity.ForecastEntity
import com.beyzaakkuzu.weather.domain.data_source.forecast.ForecastLocal
import com.beyzaakkuzu.weather.domain.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val forecastLocalDS:ForecastLocal
): BaseViewModel(){
    val weatherItem:ObservableField<ListItem> = ObservableField()
    private var forecastLiveData:LiveData<ForecastEntity> = MutableLiveData()
    var selectDayDate:String?= null
    var selectedDayForecastLiveData:MutableLiveData<List<ListItem>> = MutableLiveData()

    fun getForecastLiveData()= forecastLiveData

    fun getForecast():LiveData<ForecastEntity>{
        return forecastLocalDS.getForecast()
    }
}