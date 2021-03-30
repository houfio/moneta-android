package io.houf.moneta.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.houf.moneta.Screen.Search

@Composable
fun SearchView() {
    Column {
        TopAppBar(
            title = { Text(stringResource(Search.resourceId)) },
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.background
        )
        Text("Search")
    }
}
