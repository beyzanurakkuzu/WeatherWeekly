package com.beyzaakkuzu.weather.ui.search

import com.beyzaakkuzu.weather.core.BaseViewState
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import com.beyzaakkuzu.weather.utils.domain.Status

class SearchViewState(
    val status: Status,
    val error:String? =null,
    val data:List<CitiesForSearchEntity>? = null
): BaseViewState(status,error)