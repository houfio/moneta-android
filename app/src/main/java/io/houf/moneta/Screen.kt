package io.houf.moneta

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import io.houf.moneta.activity.SettingsActivity
import io.houf.moneta.activity.SettingsData
import io.houf.moneta.util.openActivity
import io.houf.moneta.view.PortfolioView
import io.houf.moneta.view.SearchView

sealed class Screen(
    val route: String,
    @StringRes val titleId: Int,
    val icon: ImageVector,
    val content: @Composable (Context) -> Unit,
    val actions: @Composable (Context) -> Unit = {}
) {
    object Portfolio : Screen(
        route = "portfolio",
        titleId = R.string.portfolio,
        icon = Icons.Outlined.PieChart,
        content = { context ->
            PortfolioView(context)
        },
        actions = { context ->
            IconButton(onClick = { openActivity(context, SettingsActivity(), SettingsData(currencies = listOf("Euro", "Dollar"))) }) {
                Icon(Icons.Outlined.Settings, stringResource(R.string.settings))
            }
        }
    )

    object Search : Screen(
        route = "search",
        titleId = R.string.search,
        icon = Icons.Outlined.Search,
        content = { context ->
            SearchView(context)
        }
    )
}
