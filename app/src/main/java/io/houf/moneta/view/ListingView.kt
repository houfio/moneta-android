package io.houf.moneta.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import io.houf.moneta.activity.ListingData
import io.houf.moneta.view.component.Price
import io.houf.moneta.viewmodel.ListingViewModel

@Composable
fun ListingView(data: ListingData, viewModel: ListingViewModel) {
    val listing = data.listing
    val change by viewModel.change(listing)
    val sign by viewModel.sign(listing)

    Price(viewModel.price(listing), change, sign)
}
