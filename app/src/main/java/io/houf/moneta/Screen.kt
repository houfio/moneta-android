package io.houf.moneta

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import io.houf.moneta.view.PortfolioView
import io.houf.moneta.view.SearchView

sealed class Screen(
    val route: String,
    @StringRes val titleId: Int,
    val icon: ImageVector,
    val content: @Composable (Context) -> Unit,
) {
    object Portfolio : Screen(
        route = "portfolio",
        titleId = R.string.portfolio,
        icon = Icons.Outlined.PieChart,
        content = { context ->
            PortfolioView(context)
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
