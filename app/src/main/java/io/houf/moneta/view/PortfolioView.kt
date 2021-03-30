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
import io.houf.moneta.activity.SettingsActivity
import io.houf.moneta.component.TopBar

@Composable
fun PortfolioView(controller: NavHostController, context: Context) {
    Column {
        TopBar(Portfolio) {
            IconButton({
                val intent = Intent(context, SettingsActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                context.startActivity(intent)
            }) {
                Icon(Icons.Outlined.Settings, stringResource(R.string.settings))
            }
        }
        Text("Portfolio")
        Button(onClick = { controller.navigate("listing/1") }) {
            Text("Test")
        }
    }
}
