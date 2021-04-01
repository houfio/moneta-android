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
import io.houf.moneta.util.formatNumber
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
    private var _amount = MutableLiveData(0.0)

    val plainAmount: String
        get() = _amount.value?.toBigDecimal()?.toPlainString() ?: "0.0"

    fun init(listing: ListingModel) {
        GlobalScope.launch {
            database.portfolio().get(listing.slug.toLowerCase(Locale.ROOT))?.apply {
                withContext(Dispatchers.Main) {
                    _amount.value = amount
                }
            }
        }
    }

    @Composable
    fun open() = _open.observeAsState(false)

    @Composable
    fun amount() = Transformations.map(_amount) { it.formatNumber() }.observeAsState("")

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
                _amount.value = double
            }
        }
    }

    fun validateInput(input: String) = input.toDoubleOrNull() != null

    fun getDetails(listing: ListingModel) = listOf(
        Triple(R.string.symbol, listing.symbol, Icons.Outlined.TurnedInNot),
        Triple(R.string.ranking, "#${listing.cmcRank}", Icons.Outlined.Article),
        Triple(R.string.market_capitalization, listing.q.marketCap.formatNumber(start = getSign(listing)), Icons.Outlined.ShoppingBag),
        Triple(R.string.circulating_supply, listing.circulatingSupply.formatNumber(end = " ${listing.symbol}"), Icons.Outlined.Cached),
        Triple(R.string.total_supply, listing.totalSupply.formatNumber(end = " $${listing.symbol}"), Icons.Outlined.Refresh)
    )

    fun getChange(listing: ListingModel) = listing.q.percentChange(settings.range.value ?: "")

    fun getSign(listing: ListingModel) = api.currencies.value?.find { currency ->
        currency.symbol == listing.quote.keys.first()
    }?.sign ?: ""
}
