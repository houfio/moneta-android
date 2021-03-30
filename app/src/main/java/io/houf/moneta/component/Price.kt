package io.houf.moneta.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Price(value: Double, change: Double) {
    Row(
        modifier = Modifier
            .padding(vertical = 48.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("€${String.format("%.2f", value)}", fontSize = 32.sp)
        Spacer(modifier = Modifier.width(8.dp))
        PricePill(change)
    }
}
