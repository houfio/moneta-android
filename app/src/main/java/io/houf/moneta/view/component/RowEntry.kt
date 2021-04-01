package io.houf.moneta.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.houf.moneta.R

@Composable
fun RowEntry(
    onClick: (() -> Unit)? = null,
    icon: ImageVector? = null,
    content: @Composable RowScope.() -> Unit
) {
    var modifier = Modifier.fillMaxWidth()

    if (onClick != null) {
        modifier = modifier.clickable { onClick() }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(16.dp)
    ) {
        if (icon != null) {
            Box(
                Modifier
                    .width(56.dp)
                    .fillMaxHeight(), Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = LocalContentColor.current.copy(alpha = 0.5f)
                )
            }
        }

        content()

        if (onClick != null) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = stringResource(R.string.details),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}
