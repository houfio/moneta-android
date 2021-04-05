package io.houf.moneta.view

import android.widget.Toast
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
import io.houf.moneta.util.intent.openActivity
import io.houf.moneta.view.component.Card
import io.houf.moneta.view.component.Price
import io.houf.moneta.view.component.TopBar
import io.houf.moneta.view.component.portfolio.QrButton
import io.houf.moneta.view.component.portfolio.QrDialog
import io.houf.moneta.view.component.portfolio.ShareButton
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
    val open by viewModel.open()
    val context = LocalContext.current

    if (open) {
        QrDialog(listings) {
            viewModel.setOpen(false)
        }
    }

    TopBar(Portfolio) {
        if (listings.isNotEmpty()) {
            ShareButton(value, sign)
        }
        QrButton { data ->
            viewModel.importData(data) { success ->
                Toast.makeText(
                    context,
                    if (success) R.string.import_success else R.string.import_error,
                    Toast.LENGTH_SHORT
                ).show()
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
        ) {
            viewModel.setOpen(true)
        }
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
