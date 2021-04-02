package io.houf.moneta.view

import android.Manifest
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import io.houf.moneta.util.getLocation
import io.houf.moneta.util.openActivity
import io.houf.moneta.util.sharePortfolio
import io.houf.moneta.view.component.Card
import io.houf.moneta.view.component.Price
import io.houf.moneta.view.component.TopBar
import io.houf.moneta.viewmodel.PortfolioViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioView(viewModel: PortfolioViewModel = hiltNavGraphViewModel()) {
    val listings by viewModel.listings()
    val value by viewModel.value()
    val change by viewModel.change()
    val currencies by viewModel.currencies()
    val blur by viewModel.blur()
    val sign by viewModel.sign()
    val context = LocalContext.current
    val request = registerForActivityResult(RequestPermission()) {
        getLocation(context) { location ->
            sharePortfolio(
                context,
                value,
                sign,
                location
            )
        }
    }

    TopBar(Portfolio) {
        if (listings.isNotEmpty()) {
            IconButton({
                request.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }) {
                Icon(Icons.Outlined.Share, stringResource(R.string.share))
            }
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
    if (listings.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountBalanceWallet,
                contentDescription = null,
                tint = LocalContentColor.current.copy(alpha = 0.5f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(stringResource(R.string.portfolio_empty))
        }
    } else {
        Price(
            value = value,
            change = change,
            sign = sign,
            blur = blur
        )
        LazyVerticalGrid(
            cells = GridCells.Adaptive(192.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(listings) { (listing, portfolio) ->
                Card(title = listing.name, subtitle = portfolio.amount.formatNumber()) {
                    openActivity(
                        context,
                        ListingActivity::class.java,
                        ListingData(listing)
                    )
                }
            }
        }
    }
}
