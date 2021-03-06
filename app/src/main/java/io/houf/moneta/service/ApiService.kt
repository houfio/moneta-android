package io.houf.moneta.service

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import io.houf.moneta.model.CurrenciesModel
import io.houf.moneta.model.CurrencyModel
import io.houf.moneta.model.ListingModel
import io.houf.moneta.model.ListingsModel
import io.houf.moneta.storage.Cache
import io.houf.moneta.util.ApiRequest
import io.houf.moneta.util.data.decodeJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApiService(
    private val context: Context,
    private val database: DatabaseService,
    private val settings: SettingsService
) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    private var _currencies = MutableLiveData(listOf<CurrencyModel>())
    val currencies: LiveData<List<CurrencyModel>> = _currencies

    private var _listings = MutableLiveData(listOf<ListingModel>())
    val listings: LiveData<List<ListingModel>> = _listings

    init {
        fetchCurrencies(false)
        fetchListings(false)

        settings.currency.observeForever {
            fetchListings(true)
        }
    }

    fun fetchCurrencies(skipCache: Boolean = true) = queue<CurrenciesModel>(
        url = "fiat/map",
        cacheKey = "currencies",
        skipCache = skipCache,
        onSuccess = { _currencies.value = it.data }
    )

    fun fetchListings(skipCache: Boolean = true) = queue<ListingsModel>(
        url = "cryptocurrency/listings/latest",
        cacheKey = "listings",
        skipCache = skipCache,
        query = mapOf("convert" to settings.currency.value!!),
        onSuccess = { _listings.value = it.data }
    )

    private inline fun <reified T> queue(
        url: String,
        cacheKey: String,
        skipCache: Boolean,
        query: Map<String, String> = mapOf(),
        noinline onSuccess: (T) -> Unit
    ) {
        GlobalScope.launch {
            var result: T? = null

            if (!skipCache) {
                database.cache().get(cacheKey)?.apply {
                    Log.d("moneta.cache", "Decoding cached response: $data")

                    result = decodeJson(data, T::class.java)
                }
            }

            withContext(Dispatchers.Main) {
                if (result != null) {
                    onSuccess(result!!)
                } else {
                    queue.add(ApiRequest(
                        context = context,
                        url = buildUrl(url, query),
                        cls = T::class.java,
                        onSuccess = onSuccess,
                        onCache = { cacheJson(it, cacheKey) }
                    ))
                }
            }
        }
    }

    private fun buildUrl(url: String, query: Map<String, String>): String {
        val builder = Uri.Builder()
            .scheme("https")
            .authority("pro-api.coinmarketcap.com")
            .appendPath("v1")

        url.split("/").forEach { path ->
            builder.appendPath(path)
        }

        query.forEach { (key, value) ->
            builder.appendQueryParameter(key, value)
        }

        return builder.toString()
    }

    private fun cacheJson(json: String, cacheKey: String) {
        GlobalScope.launch {
            database.cache().insert(Cache(cacheKey, json))
        }
    }
}
