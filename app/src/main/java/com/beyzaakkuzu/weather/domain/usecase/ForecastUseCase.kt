package com.beyzaakkuzu.weather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.beyzaakkuzu.weather.db.entity.ForecastEntity
import com.beyzaakkuzu.weather.other.Constants
import com.beyzaakkuzu.weather.repository.ForecastRepository
import com.beyzaakkuzu.weather.ui.dashboard.ForecastMapper
import com.beyzaakkuzu.weather.ui.dashboard.ForecastViewState
import com.beyzaakkuzu.weather.utils.Mapper
import com.beyzaakkuzu.weather.utils.UseCaseLiveData
import com.beyzaakkuzu.weather.utils.domain.Resource
import javax.inject.Inject

class ForecastUseCase @Inject internal constructor(private val repository: ForecastRepository) : UseCaseLiveData<ForecastViewState, ForecastUseCase.ForecastParams, ForecastRepository>() {

    override fun getRepository(): ForecastRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: ForecastParams?): LiveData<ForecastViewState> {
        return repository.loadForecastByCoord(
            params?.lat?.toDouble() ?: 0.0,
            params?.lon?.toDouble() ?: 0.0,
            params?.fetchRequired
                ?: false,
            units = params?.units ?: Constants.Coords.METRIC
        ).map {
            onForecastResultReady(it)
        }
    }

    private fun onForecastResultReady(resource: Resource<ForecastEntity>): ForecastViewState {
        val mappedList = resource.data?.list?.let { Mapper().map(it) }
        resource.data?.list = mappedList

        return ForecastViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class ForecastParams(
        val lat: String = "",
        val lon: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
