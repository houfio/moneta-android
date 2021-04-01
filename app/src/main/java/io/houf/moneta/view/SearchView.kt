package io.houf.moneta.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import io.houf.moneta.R
import io.houf.moneta.Screen
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.util.openActivity
import io.houf.moneta.view.component.*
import io.houf.moneta.viewmodel.SearchViewModel

@Composable
fun SearchView(viewModel: SearchViewModel = hiltNavGraphViewModel()) {
    var search by viewModel.search()
    val listings by viewModel.listings(search)

    TopBar(Screen.Search) {
        IconButton({ viewModel.refresh() }) {
            Icon(Icons.Outlined.Refresh, stringResource(R.string.refresh))
        }
    }
    SearchField(
        value = search,
        setValue = { search = it },
        modifier = Modifier.padding(bottom = 16.dp)
    )
    DividerLine()
    LazyColumn {
        items(listings) { listing ->
            val context = LocalContext.current
            val change by viewModel.change(listing)

            RowEntry({
                openActivity(
                    context,
                    ListingActivity::class.java,
                    ListingData(listing)
                )
            }) {
                Text(listing.name)
                Spacer(Modifier.weight(1f))
                Pill(change)
            }
        }
    }
}
