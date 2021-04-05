package io.houf.moneta.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.houf.moneta.model.ListingModel
import io.houf.moneta.service.ApiService
import io.houf.moneta.service.SettingsService
import io.houf.moneta.util.data.combineWith
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val api: ApiService,
    private val settings: SettingsService
) : ViewModel() {
    private var _search = MutableLiveData("")

    fun refresh() = api.fetchListings()

    @Composable
    fun search() = _search.observeAsState("")

    fun setSearch(search: String) {
        _search.value = search
    }

    @Composable
    fun listings() =
        Transformations.map(api.listings.combineWith(_search)) { (listings, search) ->
            listings?.filter { listing ->
                search == null
                        || listing.name.contains(search, true)
                        || listing.symbol.contains(search, true)
            } ?: listOf()
        }.observeAsState(listOf())

    fun change(listing: ListingModel) = settings.range.value?.let { range ->
        listing.q.percentChange(range)
    } ?: 0.0
}
