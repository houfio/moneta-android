package io.houf.moneta.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.houf.moneta.Screen

@Composable
fun TopBar(
    screen: Screen,
    controller: NavHostController? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(stringResource(screen.titleId)) },
        elevation = 0.dp,
        navigationIcon = if (controller == null) null else ({
            IconButton(onClick = { controller.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, "back")
            }
        }),
        backgroundColor = MaterialTheme.colors.background,
        actions = actions
    )
}
