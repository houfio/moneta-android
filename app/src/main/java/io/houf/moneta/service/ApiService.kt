package io.houf.moneta.service

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import io.houf.moneta.model.CurrenciesModel
import io.houf.moneta.model.CurrencyModel
import io.houf.moneta.model.ListingModel
import io.houf.moneta.model.ListingsModel
import io.houf.moneta.util.ApiRequest
import javax.inject.Singleton

@Singleton
class ApiService(private val context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    private var _currencies = MutableLiveData(listOf<CurrencyModel>())
    val currencies: LiveData<List<CurrencyModel>> = _currencies

    private var _listings = MutableLiveData(listOf<ListingModel>())
    val listings: LiveData<List<ListingModel>> = _listings

    init {
        /*getCurrencies(true) { data ->
            _currencies.value = data.data
        }
        getListings(true) { data ->
            _listings.value = data.data
        }*/
    }

    fun getCurrencies(cache: Boolean = false, onDone: (CurrenciesModel) -> Unit) =
        queue("fiat/map", onDone)

    fun getListings(cache: Boolean = false, onDone: (ListingsModel) -> Unit) =
        queue("cryptocurrency/listings/latest", onDone)

    private inline fun <reified T> queue(
        url: String,
        noinline onDone: (T) -> Unit,
        query: Map<String, String> = mapOf()
    ) {
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

        queue.add(ApiRequest(context, builder.toString(), T::class.java, onDone))
    }
}
