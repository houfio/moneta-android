package io.houf.moneta.component

import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable

@Composable
fun Line() {
    Divider(color = LocalContentColor.current.copy(alpha = 0.1f))
}