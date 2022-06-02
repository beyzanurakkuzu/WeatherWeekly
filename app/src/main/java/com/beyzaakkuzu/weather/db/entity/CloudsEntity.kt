package com.beyzaakkuzu.weather.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.beyzaakkuzu.weather.domain.model.Clouds
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Clouds")
class CloudsEntity(
    @ColumnInfo(name = "all")
    val all: Int?
):Parcelable {
    @Ignore
    constructor(
        clouds: Clouds
    ):this(
        all = clouds.all ?: 0
    )
}