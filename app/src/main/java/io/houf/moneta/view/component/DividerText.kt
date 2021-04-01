package io.houf.moneta.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DividerText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(
            start = 72.dp,
            top = 24.dp,
            bottom = 8.dp
        )
    )
}
