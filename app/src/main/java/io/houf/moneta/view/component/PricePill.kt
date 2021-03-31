package io.houf.moneta.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.houf.moneta.view.theme.Green500
import io.houf.moneta.view.theme.Red500

@Composable
fun PricePill(value: Double) {
    val positive = value >= 0

    Text(
        text = "${if (positive) "+" else ""}${String.format("%.2f", value)}%",
        color = MaterialTheme.colors.onPrimary,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clip(CircleShape)
            .background(if (positive) Green500 else Red500)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    )
}
