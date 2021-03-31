package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.SettingsService
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService
) : ViewModel() {
    val value = 1000.0
    val change = 25.0

    @Composable
    fun listings() = api.listings.observeAsState(listOf())

    @Composable
    fun currencies() = api.currencies.observeAsState(listOf())

    @Composable
    fun blur() = settings.blur.observeAsState(false)
}
