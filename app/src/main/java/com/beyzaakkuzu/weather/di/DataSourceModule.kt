package com.beyzaakkuzu.weather.di

import com.beyzaakkuzu.weather.db.dao.CitiesForSearchDAO
import com.beyzaakkuzu.weather.db.dao.CurrentWeatherDAO
import com.beyzaakkuzu.weather.db.dao.ForecastDAO
import com.beyzaakkuzu.weather.domain.WeatherApi
import com.beyzaakkuzu.weather.domain.data_source.currentWeather.CurrentWeatherLocal
import com.beyzaakkuzu.weather.domain.data_source.currentWeather.CurrentWeatherRemote
import com.beyzaakkuzu.weather.domain.data_source.forecast.ForecastLocal
import com.beyzaakkuzu.weather.domain.data_source.forecast.ForecastRemote
import com.beyzaakkuzu.weather.domain.data_source.searchCity.SearchCitiesLocal
import com.beyzaakkuzu.weather.domain.data_source.searchCity.SearchCitiesRemote
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideCurrentWeatherRemoteDataSource(api:WeatherApi)=
        CurrentWeatherRemote(api)

    @Provides
    @Singleton
    fun provideForecastRemote(api:WeatherApi)=
        ForecastRemote(api)

    @Provides
    @Singleton
    fun provideSearchCitiesRemoteDataSource(
        client: PlacesClient,
        moshi: Moshi,
    ) = SearchCitiesRemote(client, moshi)


    @Provides
    @Singleton
    fun provideCurrentWeatherLocaldataSource(currentWeatherDAO: CurrentWeatherDAO)=
        CurrentWeatherLocal(currentWeatherDAO)

    @Provides
    @Singleton
    fun provideForecastLocal(forecastDAO: ForecastDAO)=
        ForecastLocal(forecastDAO)

    @Provides
    @Singleton
    fun provideSearchCitiesLocal(citiesForSearchDAO: CitiesForSearchDAO)=
        SearchCitiesLocal(citiesForSearchDAO)
}