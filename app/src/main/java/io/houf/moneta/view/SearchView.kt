package io.houf.moneta.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.houf.moneta.Screen.Search
import io.houf.moneta.component.TopBar

@Composable
fun SearchView() {
    Column {
        TopBar(Search)
        Text("Search")
    }
}
