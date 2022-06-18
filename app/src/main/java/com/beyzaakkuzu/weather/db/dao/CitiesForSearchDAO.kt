package com.beyzaakkuzu.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity

@Dao
interface CitiesForSearchDAO {

    @Query("SELECT * FROM CitiesForSearch")
    fun getCities():LiveData<List<CitiesForSearchEntity>>

    @Query("SELECT * FROM citiesforsearch WHERE fullName like '%' || :city || '%' || '%' ORDER BY fullName DESC")
    fun getCityByName(city:String?= ""): LiveData<List<CitiesForSearchEntity>>

    @Query("DELETE FROM CitiesForSearch")
    fun deleteCities()

    @Query("SELECT count(*) from CitiesForSearch")
    fun getCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(citiesForSearchEntity: CitiesForSearchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities:List<CitiesForSearchEntity>)
}
