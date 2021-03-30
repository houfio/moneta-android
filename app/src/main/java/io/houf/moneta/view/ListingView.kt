package io.houf.moneta.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.houf.moneta.Screen.Listing
import io.houf.moneta.component.TopBar

@Composable
fun ListingView(id: Int) {
    Column {
        TopBar(Listing)
        Text("Listing $id")
    }
}
