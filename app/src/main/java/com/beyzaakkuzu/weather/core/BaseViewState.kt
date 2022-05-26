package com.beyzaakkuzu.weather.core

import com.beyzaakkuzu.weather.utils.domain.Status

open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}
