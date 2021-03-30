package io.houf.moneta.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import io.houf.moneta.R
import io.houf.moneta.Screen.Portfolio
import io.houf.moneta.activity.SettingsActivity

@Composable
fun PortfolioView(context: Context) {
    Column {
        TopAppBar(
            title = { Text(stringResource(Portfolio.resourceId)) },
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            actions = {
                IconButton(onClick = {
                    val intent = Intent(context, SettingsActivity::class.java)

                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                    ContextCompat.startActivity(context, intent, null)
                }) {
                    Icon(Icons.Outlined.Settings, stringResource(R.string.settings))
                }
            }
        )
        Text("Portfolio")
    }
}
