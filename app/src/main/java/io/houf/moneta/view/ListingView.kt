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
import io.houf.moneta.view.component.*
import io.houf.moneta.viewmodel.ListingViewModel

@Composable
fun ListingView(data: ListingData, viewModel: ListingViewModel) {
    val listing = data.listing
    val open by viewModel.open()
    val amount by viewModel.amount()

    if (open) {
        InputDialog(
            title = stringResource(R.string.update_portfolio),
            initial = viewModel.plainAmount,
            onDismiss = { viewModel.setAmount(listing, it) },
            onValidate = { viewModel.validateInput(it) }
        )
    }

    Column {
        Price(
            value = listing.q.price,
            change = viewModel.getChange(listing),
            sign = viewModel.getSign(listing)
        )
        LazyColumn {
            item {
                DividerText(stringResource(R.string.info))
            }

            items(viewModel.getDetails(listing)) { (key, value, icon) ->
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
                RowEntry({ viewModel.setOpen() }) {
                    Spacer(Modifier.width(56.dp))
                    Text(amount)
                    Spacer(Modifier.weight(1.0f))
                }
            }
        }
    }
}
