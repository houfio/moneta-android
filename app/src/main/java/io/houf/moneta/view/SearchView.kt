package io.houf.moneta.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.component.PricePill
import io.houf.moneta.util.openActivity

@Composable
fun SearchView() {
    LazyColumn {
        items(listings) { listing ->
            val name = "Coin ${listing + 1}"
            val context = LocalContext.current

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        openActivity(
                            context,
                            ListingActivity::class.java,
                            ListingData(listing, name)
                        )
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(name)
                Spacer(Modifier.weight(1f))
                PricePill(1.0)
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = "",
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}