package io.houf.moneta.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.houf.moneta.R
import io.houf.moneta.activity.ListingData
import io.houf.moneta.view.component.DividerLine
import io.houf.moneta.view.component.DividerText
import io.houf.moneta.view.component.Price
import io.houf.moneta.view.component.RowEntry
import io.houf.moneta.viewmodel.ListingViewModel

@Composable
fun ListingView(data: ListingData, viewModel: ListingViewModel) {
    val listing = data.listing
    val change by viewModel.change(listing)
    val sign by viewModel.sign(listing)
    val details by viewModel.details(listing)

    Column {
        Price(viewModel.price(listing), change, sign)
        LazyColumn {
            item {
                DividerText(stringResource(R.string.info))
            }

            items(details) { (key, value, icon) ->
                RowEntry(icon = icon) {
                    Column {
                        Text(stringResource(key))
                        Text(
                            text = value,
                            color = LocalContentColor.current.copy(alpha = 0.5f),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }

            item {
                DividerLine()
                DividerText(stringResource(R.string.portfolio))
            }

            item {
                RowEntry({}) {
                    Spacer(Modifier.width(56.dp))
                    Text("${listing.id}")
                    Spacer(Modifier.weight(1.0f))
                }
            }
        }
    }
}
