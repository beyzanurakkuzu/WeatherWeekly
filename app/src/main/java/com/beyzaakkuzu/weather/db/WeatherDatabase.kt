package com.beyzaakkuzu.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beyzaakkuzu.weather.db.dao.CitiesForSearchDAO
import com.beyzaakkuzu.weather.db.dao.CurrentWeatherDAO
import com.beyzaakkuzu.weather.db.dao.ForecastDAO
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import com.beyzaakkuzu.weather.db.entity.CurrentWeatherEntity
import com.beyzaakkuzu.weather.db.entity.ForecastEnitity
import com.beyzaakkuzu.weather.utils.typeConventer.DataConverter

@Database(
    entities = [
        ForecastEnitity::class,
    CurrentWeatherEntity::class,
    CitiesForSearchEntity::class
    ]
, version = 2)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun forecastDao():ForecastDAO
    abstract fun currentDao():CurrentWeatherDAO
    abstract fun citiesForSearchDao():CitiesForSearchDAO
}