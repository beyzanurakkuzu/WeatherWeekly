package com.beyzaakkuzu.weather.utils

interface Mapper<I,O> {
    fun map(type:I):O
}