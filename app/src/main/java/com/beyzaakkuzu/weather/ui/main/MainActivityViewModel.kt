package com.beyzaakkuzu.weather.ui.main

import androidx.databinding.ObservableField
import javax.inject.Inject


class MainActivityViewModel @Inject  internal constructor(
    var toolbarTitle:ObservableField<String> = ObservableField()
){
}