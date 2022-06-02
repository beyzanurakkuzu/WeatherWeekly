package com.beyzaakkuzu.weather.domain.model

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.parcelize.Parcelize
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.lang.Exception

@Parcelize
@JsonClass(generateAdapter = true)
data class ListItem(
    @Json(name = "dt")
    val dt: Long?,
    @Json(name = "rain")
    val rain: Rain?,
    @Json(name = "dt_txt")
    val dtTxt: String?,
    @Json(name = "snow")
    val snow: Snow?,
    @Json(name = "weather")
    val weather: List<Weather?>?,
    @Json(name = "clouds")
    val clouds: Clouds?,
    @Json(name = "sys")
    val sys: Sys?,
    @Json(name = "dt")
    val wind: Wind?,
    @Json(name = "main")
    val main: Main?
) : Parcelable {

    fun getWeatherItem(): Weather? {
        return weather?.first()
    }

    fun getDay(): String? {
        return dt?.let {
            getDateTime(it)?.getDisplayName(TextStyle.FULL, Locale.getDefault())
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            ).dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }


    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }

}

