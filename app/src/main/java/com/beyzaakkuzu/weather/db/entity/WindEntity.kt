package com.beyzaakkuzu.weather.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.beyzaakkuzu.weather.domain.model.Wind
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Wind")
data class WindEntity(
    @ColumnInfo(name="speed")
    val speed:Double?,
    @ColumnInfo(name="deg")
    val deg:Double?

):Parcelable{
    @Ignore
    constructor(
        wind: Wind?
    ):this(
        deg= wind?.deg, speed = wind?.speed
    )
}
