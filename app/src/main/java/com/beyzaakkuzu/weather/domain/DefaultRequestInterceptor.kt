package com.beyzaakkuzu.weather.domain

import com.beyzaakkuzu.weather.other.Constants
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter(
                Constants.NetworkService.API_KEY_QUERY,
                Constants.NetworkService.API_KEY
            )
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}

