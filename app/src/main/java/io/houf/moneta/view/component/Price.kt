package io.houf.moneta.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Price(value: Double, change: Double, sign: String, blur: Boolean = false) {
    BoxWithConstraints {
        val compact = constraints.maxHeight < 1000

        Column {
            Row(
                modifier = Modifier
                    .padding(vertical = if (compact) 16.dp else 48.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$sign${if (blur) "-" else String.format("%.2f", value)}",
                    fontSize = 32.sp
                )
                Spacer(Modifier.width(8.dp))
                Pill(change)
            }
            Line()
        }
    }
}
