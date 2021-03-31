package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.model.ListingModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.SettingsService
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val api: ApiService, private val settings: SettingsService) : ViewModel() {
    @Composable
    fun listings() = api.listings.observeAsState(listOf())

    fun refresh() = api.fetchListings()

    fun change(listing: ListingModel): Double {
        val quote = listing.quote.values.first()

        return when (settings.range) {
            "1h" -> quote.percentChange1h
            "7d" -> quote.percentChange7d
            else -> quote.percentChange24h
        }
    }
}
