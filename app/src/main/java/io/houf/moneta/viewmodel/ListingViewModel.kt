package io.houf.moneta.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.R
import io.houf.moneta.model.ListingModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.SettingsService
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService
) : ViewModel() {
    private fun rawSign(listing: ListingModel) = Transformations.map(api.currencies) { currencies ->
        currencies.find { it.symbol == listing.quote.keys.first() }?.sign ?: "€"
    }

    fun price(listing: ListingModel): Double {
        val quote = listing.quote.values.first()

        return quote.price
    }

    @Composable
    fun details(listing: ListingModel) = Transformations.map(rawSign(listing)) { sign ->
        val symbol = listing.symbol

        listOf(
            Triple(R.string.symbol, symbol, Icons.Outlined.TurnedInNot),
            Triple(R.string.ranking, "#${listing.cmcRank}", Icons.Outlined.Article),
            Triple(R.string.market_capitalization, "$sign${String.format("%.2f", listing.quote.values.first().marketCap)}", Icons.Outlined.ShoppingBag),
            Triple(R.string.circulating_supply, "${String.format("%.2f", listing.circulatingSupply)} $symbol", Icons.Outlined.Cached),
            Triple(R.string.total_supply, "${String.format("%.2f", listing.totalSupply)} $symbol", Icons.Outlined.Refresh)
        )
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

    @Composable
    fun sign(listing: ListingModel) = rawSign(listing).observeAsState("")
}
