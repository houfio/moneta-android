package io.houf.moneta.view

import android.content.Context
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.ListingData
import io.houf.moneta.util.openActivity

@Composable
fun PortfolioView(context: Context) {
    Text("Portfolio")
    Button(onClick = {
        openActivity(context, ListingActivity(), ListingData(1000, "Bitcoin"))
    }) {
        Text("Open listing")
    }
}
