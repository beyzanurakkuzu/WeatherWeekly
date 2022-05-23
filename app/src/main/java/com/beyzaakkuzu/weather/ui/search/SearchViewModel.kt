package com.beyzaakkuzu.weather.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.beyzaakkuzu.weather.core.BaseViewModel
import com.beyzaakkuzu.weather.db.entity.CoordEntity
import com.beyzaakkuzu.weather.domain.usecase.SearchCitiesUseCase
import com.beyzaakkuzu.weather.other.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject internal constructor(
    private val useCase:SearchCitiesUseCase,
    private val pref:SharedPreferences
): BaseViewModel(){
    private val searchParams:MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    fun getSearchViewState() = searchViewState
    private val searchViewState:LiveData<SearchViewState> = searchParams.switchMap {
        params -> useCase.execute(params)
    }
    fun setSearchParams(params:SearchCitiesUseCase.SearchCitiesParams){
        if(searchParams.value== params){
            return
        }
        searchParams.postValue(params)
    }
    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>?
    {
        return Single.create<String>{
            pref.edit().putString(Constants.Coords.LAT,coordEntity.lat.toString()).apply()
            pref.edit().putString(Constants.Coords.LON,coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}