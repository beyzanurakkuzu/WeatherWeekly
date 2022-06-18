package com.beyzaakkuzu.weather.ui.dashboard

import com.beyzaakkuzu.weather.domain.model.ListItem
import com.beyzaakkuzu.weather.utils.Mapper
import javax.inject.Inject

class ForecastMapper @Inject constructor() : Mapper<List<ListItem>, List<ListItem>> {
    override fun map(type: List<ListItem>): List<ListItem> {
        val days = arrayListOf<String>()
        val mappedArray = arrayListOf<ListItem>()

        type.forEachIndexed { _, listItem ->
            // Add day to days
            if (days.contains(listItem.dtTxt?.substringBefore(" ")).not()) {
                listItem.dtTxt?.substringBefore(" ")?.let { days.add(it) }
            }
        }

        days.forEach { day ->

            // Find min and max temp values each of the day
            val array = type.filter { it.dtTxt?.substringBefore(" ").equals(day) }

            val minTemp = array.minByOrNull { it.main?.tempMin ?: 0.0 }?.main?.tempMin
            val maxTemp = array.maxByOrNull { it.main?.tempMax ?: 0.0 }?.main?.tempMax

            array.forEach {
                it.main?.tempMin = minTemp
                it.main?.tempMax = maxTemp
                mappedArray.add(it)
            }
        }

        return mappedArray
            .filter { it.dtTxt?.substringAfter(" ")?.substringBefore(":")?.toInt()!! >= 12 }
            .distinctBy { it.getDay() } // Eliminate same days
            .toList() // Return as list
    }
}
