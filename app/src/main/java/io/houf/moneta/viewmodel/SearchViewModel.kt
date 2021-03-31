package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.model.ListingModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.SettingsService
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService
) : ViewModel() {
    fun refresh() = api.fetchListings()

    @Composable
    fun search() = remember { mutableStateOf("") }

    @Composable
    fun listings(search: String) = Transformations.map(api.listings) { listings ->
        listings.filter { listing ->
            listing.name.contains(search, true) || listing.symbol.contains(search, true)
        }
    }.observeAsState(listOf())

    @Composable
    fun change(listing: ListingModel) = Transformations.map(settings.range) { range ->
        val quote = listing.quote.values.first()

        when (range) {
            "1h" -> quote.percentChange1h
            "7d" -> quote.percentChange7d
            else -> quote.percentChange24h
        }
    }.observeAsState(0.0)
}
