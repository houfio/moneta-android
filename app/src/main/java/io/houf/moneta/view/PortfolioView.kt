package io.houf.moneta.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import io.houf.moneta.R
import io.houf.moneta.Screen.Portfolio
import io.houf.moneta.activity.ListingActivity
import io.houf.moneta.activity.SettingsActivity
import io.houf.moneta.component.TopBar
import io.houf.moneta.util.openActivity

@Composable
fun PortfolioView(context: Context) {
    Column {
        TopBar(Portfolio) {
            IconButton(onClick = { openActivity(context, SettingsActivity::class.java) }) {
                Icon(Icons.Outlined.Settings, stringResource(R.string.settings))
            }
        }
        Text("Portfolio")
        Button(onClick = { openActivity(context, ListingActivity::class.java) }) {
            Text("Open listing")
        }
    }
}
