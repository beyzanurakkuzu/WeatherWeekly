package com.beyzaakkuzu.weather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import com.beyzaakkuzu.weather.repository.SearchCitiesRepository
import com.beyzaakkuzu.weather.ui.search.SearchViewState
import com.beyzaakkuzu.weather.utils.UseCaseLiveData
import com.beyzaakkuzu.weather.utils.domain.Resource
import javax.inject.Inject

class SearchCitiesUseCase @Inject internal constructor(
    private val repo: SearchCitiesRepository
) : UseCaseLiveData<SearchViewState, SearchCitiesUseCase.SearchCitiesParams, SearchCitiesRepository>() {
    override fun getRepository(): SearchCitiesRepository = repo

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<SearchViewState> {

        return repo.loadCitiesByCityName(
            cityName = params?.city ?: ""
        ).map {
            onSearchResultReady(it)
        }
    }

    private fun onSearchResultReady(resource: Resource<List<CitiesForSearchEntity>>):SearchViewState{
        return SearchViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}