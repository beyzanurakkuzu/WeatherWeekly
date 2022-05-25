package com.beyzaakkuzu.weather.ui.dashboard.forecast

import androidx.databinding.ObservableField
import com.beyzaakkuzu.weather.core.BaseViewModel
import com.beyzaakkuzu.weather.domain.model.ListItem
import javax.inject.Inject

class ForecastItemViewModel @Inject internal constructor():BaseViewModel(){
    var item = ObservableField<ListItem>()
}