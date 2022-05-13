package com.beyzaakkuzu.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.beyzaakkuzu.weather.db.entity.ForecastEnitity
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ForecastDAO {

    @Query("SELECT * FROM Forecast")
    fun getForecast(): LiveData<ForecastEnitity>

    @Query("SELECT * FROM Forecast ORDER BY abs(lat-:lat) AND abs(lon-:lon) LIMIT 1")
    fun getForecastByCoord(lat: Double, lon: Double): LiveData<ForecastEnitity>

    @Insert(onConflict = REPLACE)
    fun insertForecast(forecast: ForecastEnitity)

    @Transaction
    fun deleteAndInsert(forecast: ForecastEnitity) {
        deleteAll()
        insertForecast(forecast)
    }

    @Update
    fun updateForecast(forecast: ForecastEnitity)

    @Delete
    fun deleteForecast(forecast: ForecastEnitity)

    @Query("DELETE FROM Forecast")
    fun deleteAll()

    @Query("Select count(*) from Forecast")
    fun getCount(): Int

}