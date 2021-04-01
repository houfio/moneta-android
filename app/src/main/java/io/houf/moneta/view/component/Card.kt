package io.houf.moneta.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Card(title: String, subtitle: String, onClick: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(LocalContentColor.current.copy(alpha = 0.1f))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(title)
        Text(
            text = subtitle,
            fontSize = 22.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
