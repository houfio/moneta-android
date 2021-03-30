package io.houf.moneta.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.houf.moneta.Screen

@Composable
fun TopBar(screen: Screen, actions: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        title = { Text(stringResource(screen.titleId)) },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        actions = actions
    )
}
