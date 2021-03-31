package io.houf.moneta.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.houf.moneta.activity.ListingData

@Composable
fun ListingView(data: ListingData) {
    val listing = data.listing

    Text("Listing ${listing.id}")
}
