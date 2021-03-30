package io.houf.moneta

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import io.houf.moneta.view.ListingView
import io.houf.moneta.view.PortfolioView
import io.houf.moneta.view.SearchView

sealed class Screen(
    val route: String,
    @StringRes val titleId: Int,
    val icon: ImageVector,
    val navigable: Boolean = false,
    val arguments: List<NamedNavArgument> = listOf(),
    val content: @Composable (NavHostController, NavBackStackEntry, Context) -> Unit,
) {
    object Portfolio : Screen(
        route = "portfolio",
        titleId = R.string.portfolio,
        icon = Icons.Outlined.PieChart,
        navigable = true,
        content = { controller, _, context ->
            PortfolioView(controller, context)
        }
    )

    object Search : Screen(
        route = "search",
        titleId = R.string.search,
        icon = Icons.Outlined.Search,
        navigable = true,
        content = { _, _, _ ->
            SearchView()
        }
    )

    object Listing : Screen(
        route = "listing/{id}",
        titleId = R.string.listing,
        icon = Icons.Outlined.Money,
        arguments = listOf(navArgument("id") { type = NavType.IntType }),
        content = { _, stack, _ ->
            ListingView(stack.arguments?.getInt("id") ?: -1)
        }
    )
}
