package io.houf.moneta.view

import android.content.Context
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.util.openActivity

@Composable
fun PortfolioView(context: Context) {
    Text("Portfolio")
    Button(onClick = { openActivity(context, ListingActivity::class.java) }) {
        Text("Open listing")
    }
}
