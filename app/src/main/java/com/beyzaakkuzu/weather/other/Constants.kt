package com.beyzaakkuzu.weather.other

object Constants {

    object NetworkService{
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        const val RATE_LIMITER_TYPE="data"
        const val API_KEY="6d0cda511bc2fee06117d2803bf10494"
        const val API_KEY_QUERY = "appid"

    }
    object Coords{
        const val LAT="lat"
        const val LON="lon"
        const val METRIC="metric"
    }
    object AlgoliaKeys {
        const val APPLICATION_ID = "BOK32FXG3M"
        const val SEARCH_API_KEY = "26674fe1182a94390110a919ebc5afc5"
    }
}