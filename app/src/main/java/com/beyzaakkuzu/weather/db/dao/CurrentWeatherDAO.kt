package com.beyzaakkuzu.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.beyzaakkuzu.weather.db.entity.CurrentWeatherEntity

@Dao
interface CurrentWeatherDAO {

    @Query("SELECT * FROM CurrentWeather")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntity>

    @Query("DELETE FROM CurrentWeather")
    fun deleteCurrentWeather()

    @Query("SELECT count(*) FROM CurrentWeather")
    fun getCount()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)
    @Transaction
    fun deleteAndInsert(currentWeatherEntity: CurrentWeatherEntity) {
        deleteCurrentWeather()
        insertCurrentWeather(currentWeatherEntity)
    }

}