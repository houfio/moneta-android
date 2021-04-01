package io.houf.moneta.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import io.houf.moneta.R
import io.houf.moneta.Screen.Portfolio
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.activity.SettingsActivity
import io.houf.moneta.activity.SettingsData
import io.houf.moneta.util.formatNumber
import io.houf.moneta.util.openActivity
import io.houf.moneta.util.share
import io.houf.moneta.view.component.Card
import io.houf.moneta.view.component.Price
import io.houf.moneta.view.component.TopBar
import io.houf.moneta.viewmodel.PortfolioViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioView(viewModel: PortfolioViewModel = hiltNavGraphViewModel()) {
    val listings by viewModel.listings()
    val currencies by viewModel.currencies()
    val blur by viewModel.blur()
    val context = LocalContext.current

    TopBar(Portfolio) {
        IconButton({
            share(
                context,
                viewModel.value
            )
        }) {
            Icon(Icons.Outlined.Share, stringResource(R.string.share))
        }
        IconButton({
            openActivity(
                context,
                SettingsActivity::class.java,
                SettingsData(currencies)
            )
        }) {
            Icon(Icons.Outlined.Settings, stringResource(R.string.settings))
        }
    }
    Price(
        value = viewModel.value,
        change = viewModel.change,
        sign = "€",
        blur = blur
    )
    LazyVerticalGrid(
        cells = GridCells.Adaptive(192.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listings) { (listing, portfolio) ->
            Card(title = listing.name, subtitle = portfolio?.amount.formatNumber()) {
                openActivity(
                    context,
                    ListingActivity::class.java,
                    ListingData(listing)
                )
            }
        }
    }
}
