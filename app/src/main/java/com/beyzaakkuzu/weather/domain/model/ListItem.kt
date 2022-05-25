package com.beyzaakkuzu.weather.domain.model

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

    fun getColor(): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#4ECB71") //yeşil
            DayOfWeek.TUESDAY -> Color.parseColor("#FF9A62") //turuncu
            DayOfWeek.WEDNESDAY -> Color.parseColor("#0D99FF") //mavi
            DayOfWeek.THURSDAY -> Color.parseColor("#9747FF") //mor
            DayOfWeek.FRIDAY -> Color.parseColor("#FFEA29") //sarı
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF") //kırmızı
            DayOfWeek.SUNDAY -> Color.parseColor("#174AB7")
            else -> Color.parseColor("#24B794")
        }
    }

    fun getHourColor(): Int {
        return when (dtTxt?.substringAfter(" ")?.substringBeforeLast(":")) {
            "00:00" -> Color.parseColor("#24B794")
            "03:00" -> Color.parseColor("#174AB7")
            "06:00" -> Color.parseColor("#0051FF")
            "09:00" -> Color.parseColor("#FFEA29")
            "12:00" -> Color.parseColor("#9747FF")
            "15:00" -> Color.parseColor("#0D99FF")
            "18:00" -> Color.parseColor("#FF9A62")
            "21:00" -> Color.parseColor("#4ECB71")
            else -> Color.parseColor("24B794")
        }
    }

    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }

}

