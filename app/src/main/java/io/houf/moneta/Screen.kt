package io.houf.moneta

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Portfolio : Screen("portfolio", R.string.portfolio, Icons.Outlined.PieChart)
    object Search : Screen("search", R.string.search, Icons.Outlined.Search)
}
