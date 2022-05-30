package com.beyzaakkuzu.weather.ui.splash

import android.content.SharedPreferences
import com.beyzaakkuzu.weather.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    var sharedPreferences: SharedPreferences
):BaseViewModel(){
    var navigate = false
}