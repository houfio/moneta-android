package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.model.PortfolioModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.DatabaseService
import io.houf.moneta.service.SettingsService
import io.houf.moneta.storage.Portfolio
import io.houf.moneta.util.data.combineWith
import io.houf.moneta.util.data.decodePortfolio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService,
    private val database: DatabaseService
) : ViewModel() {
    private val _listings = Transformations.map(
        api.listings.combineWith(database.portfolio().get())
    ) { (listings, portfolio) ->
        listings?.mapNotNull { listing ->
            val p = portfolio?.find { it.id.equals(listing.slug, true) }

            if (p != null && p.amount > 0) PortfolioModel(listing, p) else null
        } ?: listOf()
    }
    private val _value = Transformations.map(_listings) { listings ->
        listings.fold(0.0) { acc, p -> acc + p.listing.q.price * p.portfolio.amount }
    }
    private var _open = MutableLiveData(false)

    @Composable
    fun listings() = _listings.observeAsState(listOf())

    @Composable
    fun value() = _value.observeAsState(0.0)

    @Composable
    fun change() = Transformations.map(
        _listings.combineWith(
            _value,
            settings.range
        )
    ) { (portfolio, value, range) ->
        portfolio?.fold(0.0) { acc, p ->
            if (value == null || range == null) {
                return@fold acc
            }

            val worth = p.listing.q.price * p.portfolio.amount
            val weight = 1 / value * worth

            acc + p.listing.q.percentChange(range) * weight
        } ?: 0.0
    }.observeAsState(0.0)

    @Composable
    fun currencies() = api.currencies.observeAsState(listOf())

    @Composable
    fun blur() = settings.blur.observeAsState(false)

    @Composable
    fun sign() =
        Transformations.map(api.currencies.combineWith(settings.currency)) { (currencies, currency) ->
            currencies?.find { it.symbol.equals(currency, true) }?.sign ?: ""
        }.observeAsState("")

    @Composable
    fun open() = _open.observeAsState(false)

    fun setOpen(open: Boolean) {
        _open.value = open
    }

    fun importData(data: String, onResult: (Boolean) -> Unit) {
        val list: List<Portfolio>

        try {
            list = decodePortfolio(data)
        } catch (e: Exception) {
            onResult(false)

            return
        }

        GlobalScope.launch {
            database.portfolio().clear()
            database.portfolio().insertMany(list)

            withContext(Dispatchers.Main) {
                onResult(true)
            }
        }
    }
}
