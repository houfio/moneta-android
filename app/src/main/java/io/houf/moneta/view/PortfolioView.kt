package io.houf.moneta.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import io.houf.moneta.R
import io.houf.moneta.Screen.Portfolio
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.activity.SettingsActivity
import io.houf.moneta.activity.SettingsData
import io.houf.moneta.util.openActivity
import io.houf.moneta.view.component.Price
import io.houf.moneta.view.component.TopBar
import io.houf.moneta.viewmodel.PortfolioViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioView(viewModel: PortfolioViewModel = hiltNavGraphViewModel()) {
    val listings by viewModel.listings()
    val currencies by viewModel.currencies()
    val context = LocalContext.current

    TopBar(Portfolio) {
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
    Price(viewModel.value, viewModel.change)
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 192.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listings) { listing ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(LocalContentColor.current.copy(alpha = 0.1f))
                    .clickable {
                        openActivity(
                            context,
                            ListingActivity::class.java,
                            ListingData(listing)
                        )
                    }
                    .padding(16.dp)
            ) {
                Text(listing.name)
                Text(
                    text = "${listing.id}",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
