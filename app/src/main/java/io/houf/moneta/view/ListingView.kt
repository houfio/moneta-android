package io.houf.moneta.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import io.houf.moneta.Screen.Listing
import io.houf.moneta.component.TopBar

@Composable
fun ListingView(id: Int, controller: NavHostController) {
    Column {
        TopBar(Listing, controller)
        Text("Listing $id")
    }
}
