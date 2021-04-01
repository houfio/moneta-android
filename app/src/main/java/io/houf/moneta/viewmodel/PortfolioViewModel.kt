package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val database: DatabaseService
) : ViewModel() {
    val value = 1000.0
    val change = 25.0

    @Composable
    fun listings() =
        Transformations.map(api.listings.combineWith(database.portfolio().get())) { (listings, portfolio) ->
            listings?.map { listing ->
                listing to portfolio?.find { it.id == listing.slug.toLowerCase(Locale.ROOT) }
            }?.filter { (_, portfolio) ->
                portfolio != null && portfolio.amount > 0
            } ?: listOf()
        }.observeAsState(listOf())

    @Composable
    fun currencies() = api.currencies.observeAsState(listOf())

    @Composable
    fun blur() = settings.blur.observeAsState(false)
}
