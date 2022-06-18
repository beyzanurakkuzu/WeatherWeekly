package com.beyzaakkuzu.weather.ui.weather_detail.weatherHourOfDay

import androidx.databinding.ObservableField
import com.beyzaakkuzu.weather.core.BaseViewModel
import com.beyzaakkuzu.weather.domain.model.ListItem
import javax.inject.Inject

class WeatherHourOfDayItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<ListItem>()
}