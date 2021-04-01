package io.houf.moneta.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.R
import io.houf.moneta.model.ListingModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.DatabaseService
import io.houf.moneta.service.SettingsService
import io.houf.moneta.storage.Portfolio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService,
    private val database: DatabaseService
) : ViewModel() {
    private var _open = MutableLiveData(false)
    private var _amount = MutableLiveData("0.0")

    fun initialize(listing: ListingModel) {
        GlobalScope.launch {
            database.portfolio().get(listing.slug.toLowerCase(Locale.ROOT))?.apply {
                withContext(Dispatchers.Main) {
                    _amount.value = amount.toBigDecimal().toPlainString()
                }
            }
        }
    }

    private fun rawSign(listing: ListingModel) = Transformations.map(api.currencies) { currencies ->
        currencies.find { it.symbol == listing.quote.keys.first() }?.sign ?: "€"
    }

    @Composable
    fun open() = _open.observeAsState(false)

    @Composable
    fun amount() = _amount.observeAsState("")

    fun setOpen() {
        _open.value = true
    }

    fun setAmount(listing: ListingModel, amount: String) {
        GlobalScope.launch {
            val double = amount.toDouble()

            database.portfolio().insert(Portfolio(
                id = listing.slug.toLowerCase(Locale.ROOT),
                amount = double
            ))

            withContext(Dispatchers.Main) {
                _open.value = false
                _amount.value = double.toBigDecimal().toPlainString()
            }
        }
    }

    @Composable
    fun details(listing: ListingModel) = Transformations.map(rawSign(listing)) { sign ->
        val symbol = listing.symbol

        listOf(
            Triple(R.string.symbol, symbol, Icons.Outlined.TurnedInNot),
            Triple(R.string.ranking, "#${listing.cmcRank}", Icons.Outlined.Article),
            Triple(R.string.market_capitalization, "$sign${String.format("%.2f", listing.q.marketCap)}", Icons.Outlined.ShoppingBag),
            Triple(R.string.circulating_supply, "${String.format("%.2f", listing.circulatingSupply)} $symbol", Icons.Outlined.Cached),
            Triple(R.string.total_supply, "${String.format("%.2f", listing.totalSupply)} $symbol", Icons.Outlined.Refresh)
        )
    }.observeAsState(listOf())

    @Composable
    fun change(listing: ListingModel) =
        Transformations.map(settings.range) { listing.q.percentChange(it) }.observeAsState(0.0)

    @Composable
    fun sign(listing: ListingModel) = rawSign(listing).observeAsState("")
}
