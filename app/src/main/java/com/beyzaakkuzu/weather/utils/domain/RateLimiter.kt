package com.beyzaakkuzu.weather.utils.domain

import android.util.ArrayMap
import java.util.concurrent.TimeUnit

class RateLimiter<in KEY>(timeout:Int,timeUnit:TimeUnit) {
    private val timestamp= ArrayMap<KEY,Long>()
    private val timeout= timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun reset(key:KEY){
        timestamp.remove(key)
    }
}