package com.beyzaakkuzu.weather.domain.data_source.searchCity

import com.algolia.search.saas.places.PlacesClient
import com.algolia.search.saas.places.PlacesQuery
import com.beyzaakkuzu.weather.domain.model.Search
import com.beyzaakkuzu.weather.utils.extensions.tryCatch
import com.squareup.moshi.Moshi
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class SearchCitiesRemote @Inject constructor(
    private val client: PlacesClient,
    private val moshi: Moshi
) {
    fun getCityWithQuery(query: String): Single<Search> {
        return Single.create { single ->
            val algoliaQuery = PlacesQuery(query)
                .setLanguage("en")
                .setHitsPerPage(25)

            client.searchAsync(algoliaQuery) { json, exception ->
                if (exception == null) {
                    tryCatch(
                        tryBlock = {
                            val adapter = moshi.adapter(Search::class.java)
                            val data = adapter.fromJson(json.toString())
                            if (data?.hits != null) {
                                single.onSuccess(data)
                            }
                        },
                        catchBlock = {
                            Timber.e(it, it.localizedMessage)
                        }
                    )
                } else {
                    single.onError(Throwable("Cant find '$query'. Please try another one."))
                }
            }
        }
    }
}