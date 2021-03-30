package io.houf.moneta

import android.content.Context
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import io.houf.moneta.Screen.*
import io.houf.moneta.view.PortfolioView
import io.houf.moneta.view.SearchView

val screens = listOf(Portfolio, Search)

@Composable
fun MonetaApp(context: Context) {
    val controller = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
                val navigationState by controller.currentBackStackEntryAsState()
                val currentRoute = navigationState?.arguments?.getString(KEY_ROUTE)

                screens.forEach { screen ->
                    val label = stringResource(screen.resourceId)

                    BottomNavigationItem(
                        selected = currentRoute == screen.route,
                        icon = { Icon(screen.icon, label) },
                        label = { Text(label) },
                        alwaysShowLabel = false,
                        onClick = {
                            controller.navigate(screen.route) {
                                popUpTo = controller.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(controller, startDestination = Portfolio.route) {
            composable(Portfolio.route) { PortfolioView(context) }
            composable(Search.route) { SearchView() }
        }
    }
}
