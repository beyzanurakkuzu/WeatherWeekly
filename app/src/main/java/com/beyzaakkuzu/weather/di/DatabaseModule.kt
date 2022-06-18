package com.beyzaakkuzu.weather.di

import android.content.Context
import androidx.room.Room
import com.beyzaakkuzu.weather.db.WeatherDatabase
import com.beyzaakkuzu.weather.db.dao.CitiesForSearchDAO
import com.beyzaakkuzu.weather.db.dao.CurrentWeatherDAO
import com.beyzaakkuzu.weather.db.dao.ForecastDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):WeatherDatabase=
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "WeatherAppDB"
        ).build()

    @Provides
    @Singleton
    fun provideForecastDao(db:WeatherDatabase):ForecastDAO=db.forecastDao()

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(db:WeatherDatabase):CurrentWeatherDAO= db.currentDao()

    @Provides
    @Singleton
    fun provideCitiesForSearchDao(db:WeatherDatabase):CitiesForSearchDAO= db.citiesForSearchDao()
}