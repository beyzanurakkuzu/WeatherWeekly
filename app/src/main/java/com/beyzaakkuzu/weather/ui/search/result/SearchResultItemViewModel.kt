package com.beyzaakkuzu.weather.ui.search.result

import androidx.databinding.ObservableField
import com.beyzaakkuzu.weather.core.`BaseViewModel()`
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultItemViewModel @Inject internal constructor():`BaseViewModel()`(){
    val item = ObservableField<CitiesForSearchEntity>()
}