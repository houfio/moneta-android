package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.service.ApiService
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    @Composable
    fun listings() = api.listings.observeAsState(listOf())
}
