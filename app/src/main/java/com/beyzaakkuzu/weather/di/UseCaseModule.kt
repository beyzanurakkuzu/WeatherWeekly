package com.beyzaakkuzu.weather.di

import com.beyzaakkuzu.weather.domain.usecase.CurrentWeatherUseCase
import com.beyzaakkuzu.weather.repository.CurrentWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideCurrentWeatherUseCase(cwr:CurrentWeatherRepository)= CurrentWeatherUseCase(cwr)

   /* @Provides
    @Singleton
    fun provideForecastUseCase(fr:ForecastRepository)=
        ForecastUseCase(fr)

    @Provides
    @Singleton
    fun provideSearchCitiesUseCase(scr:SearchCitiesRepository)=
        SearchCitiesUseCase(scr)*/
}