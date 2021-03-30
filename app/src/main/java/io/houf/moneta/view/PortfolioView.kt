package io.houf.moneta.view

import android.content.Context
import android.os.Bundle
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.util.openActivity

@Composable
fun PortfolioView(context: Context) {
    Text("Portfolio")
    Button(onClick = {
        openActivity(context, ListingActivity::class.java, Bundle().apply {
            putInt("id", 1000)
        })
    }) {
        Text("Open listing")
    }
}
