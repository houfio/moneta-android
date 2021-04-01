package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.model.PortfolioModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.DatabaseService
import io.houf.moneta.service.SettingsService
import io.houf.moneta.util.combineWith
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService,
    database: DatabaseService
) : ViewModel() {
    private val listings = Transformations.map(
        api.listings.combineWith(database.portfolio().get())
    ) { (listings, portfolio) ->
        listings?.mapNotNull { listing ->
            val p = portfolio?.find { it.id == listing.slug.toLowerCase(Locale.ROOT) }

            if (p != null && p.amount > 0) PortfolioModel(listing, p) else null
        } ?: listOf()
    }

    private val value = Transformations.map(listings) { listings ->
        listings.fold(0.0) { acc, p -> acc + p.listing.q.price * p.portfolio.amount }
    }

    @Composable
    fun listings() = listings.observeAsState(listOf())

    @Composable
    fun value() = value.observeAsState(0.0)

    @Composable
    fun change() = Transformations.map(listings.combineWith(value, settings.range)) { (portfolio, value, range) ->
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
}
