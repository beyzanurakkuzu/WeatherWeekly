package com.beyzaakkuzu.weather.db.entity

import android.os.Parcelable
import androidx.room.*
import com.beyzaakkuzu.weather.domain.model.ListItem
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Forecast")
class ForecastEnitity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @Embedded
    var city: CityEntity?,

    @ColumnInfo(name = "list")
    var list: List<ListItem>
) : Parcelable {
    @Ignore
    constructor(forecastEnitity: ForecastEnitity) : this(
        id = 0,
        city = forecastEnitity.city?.let { CityEntity(it) },
        list = forecastEnitity.list
    )

}