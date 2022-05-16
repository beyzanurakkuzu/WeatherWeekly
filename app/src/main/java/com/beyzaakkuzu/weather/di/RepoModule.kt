package com.beyzaakkuzu.weather.di

import com.beyzaakkuzu.weather.domain.data_source.currentWeather.CurrentWeatherLocal
import com.beyzaakkuzu.weather.domain.data_source.currentWeather.CurrentWeatherRemote
import com.beyzaakkuzu.weather.repository.CurrentWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {
    @Provides
    @Singleton
    fun provideCurrentWeatherRepository(
        cwr:CurrentWeatherRemote,
        cwl:CurrentWeatherLocal
    ) = CurrentWeatherRepository(cwr, cwl)

 /*   @Provides
    @Singleton
    fun provideForecastRepository(
        fr:ForecastRemote,
        fl:ForecastLocal
    ) = ForecastRepository(fr,fl)*/

  /*  @Provides
    @Singleton
    fun provideSearchCitiesRepository(scr:SearchCitiesRemote,scl:SearchCitiesLocal
    )= SearchCitiesRepository(scr,scl)*/
}