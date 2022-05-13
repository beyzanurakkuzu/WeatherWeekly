package com.beyzaakkuzu.weather.db.entity

import android.graphics.Color
import android.os.Parcelable
import androidx.room.*
import com.beyzaakkuzu.weather.domain.model.CurrentWeatherResponse
import com.beyzaakkuzu.weather.domain.model.Weather
import kotlinx.parcelize.Parcelize
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = "CurrentWeather")
data class CurrentWeatherEntity(
    @ColumnInfo(name = "visibility")
    var visibility: Int?,
    @ColumnInfo(name = "timezone")
    var timezone: Int?,
    @Embedded
    var main: MainEntity?,
    @Embedded
    var clouds: CloudsEntity?,
    @ColumnInfo(name = "dt")
    var dt: Long?,
    @ColumnInfo(name = "weather")
    val weather: List<Weather?>? = null,
    @ColumnInfo(name = "name")
    val name: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "base")
    val base: String?,
    @Embedded
    val wind: WindEntity?
):Parcelable {

    @Ignore
    constructor(
        cws: CurrentWeatherResponse
    ):this(
        visibility=cws.visibility,
        timezone=cws.timezone,
        main= MainEntity(cws.main),
        clouds= cws.clouds?.let { CloudsEntity(it) },
        dt=cws.dt?.toLong(),
        weather=cws.weather,
        name=cws.name,
        id=0,
        base=cws.base,
        wind= WindEntity(cws.wind)
    )

    fun getCurrentWeather():Weather?{
        return weather?.first()
    }

    private fun getDateTime(s:Long):DayOfWeek?{
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }
    fun getColor():Int{
        return when (dt?.let { getDateTime(it) }){
            DayOfWeek.MONDAY -> Color.parseColor("#4ECB71") //yeşil
            DayOfWeek.TUESDAY -> Color.parseColor("#FF9A62") //turuncu
            DayOfWeek.WEDNESDAY -> Color.parseColor("#0D99FF") //mavi
            DayOfWeek.THURSDAY -> Color.parseColor("#9747FF") //mor
            DayOfWeek.FRIDAY -> Color.parseColor("#FFEA29") //sarı
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF") //kırmızı
            DayOfWeek.SUNDAY -> Color.parseColor("#174AB7")
            else -> Color.parseColor("#24B794")        }
    }
}