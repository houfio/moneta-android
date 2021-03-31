package io.houf.moneta.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import io.houf.moneta.model.ListingModel
import io.houf.moneta.model.ListingResponseModel
import io.houf.moneta.util.add

class PortfolioViewModel(queue: RequestQueue) : ViewModel() {
    var listings by mutableStateOf<List<ListingModel>>(listOf())
        private set

    val value = 1000.0
    val change = 25.0

    init {
        queue.add<ListingResponseModel>("cryptocurrency/listings/latest", {}) { data ->
            Log.d("MONETA", "Fetched ${data.data.size} listings")

            listings = data.data
        }
    }
}
