package com.beyzaakkuzu.weather.db.entity

import android.os.CountDownTimer
import android.os.Parcelable
import android.text.SpannableString
import android.text.SpannedString
import androidx.room.*
import com.beyzaakkuzu.weather.domain.model.HitsItem
import com.beyzaakkuzu.weather.utils.extensions.bold
import com.beyzaakkuzu.weather.utils.extensions.italic
import com.beyzaakkuzu.weather.utils.extensions.plus
import com.beyzaakkuzu.weather.utils.extensions.spannable
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "CitiesForSearch")
class CitiesForSearchEntity(
    @ColumnInfo(name="administractive")
    val administrative:String?,
    @ColumnInfo(name="country")
    val country:String?,
    @ColumnInfo(name="fullName")
    val name:String?,
    @Embedded
    val coord:CoordEntity?,
    @ColumnInfo(name="county")
    val county: String?,
    @ColumnInfo(name="importance")
    val importance:Int?,
    @PrimaryKey
    @ColumnInfo(name="Id")
    val id:String

    ):Parcelable {
    @Ignore
    constructor(histItem: HitsItem?
    ):this(
        administrative=histItem?.administrative?.first(),
        country=histItem?.country,
        name=histItem?.localeNames?.first(),
        coord= CoordEntity(histItem?.geoloc),
        county=histItem?.country,
        importance=histItem?.importance,
        id=histItem?.objectID.toString()
    )
    fun getFullName(): SpannableString { //AnyExt.kt extensions
        return spannable {
            bold(name ?: "").plus(", ") +
                    bold(county ?: "").plus(", ") +
                    italic(administrative ?: "").plus(", ") +
                    italic(country ?: "")
        }
    }
}
