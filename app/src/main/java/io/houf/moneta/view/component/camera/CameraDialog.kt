package io.houf.moneta.view.component.camera

import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.houf.moneta.R

@Composable
fun CameraDialog(
    onDismiss: (String?) -> Unit,
) {
    val context = LocalContext.current

    Dialog({ onDismiss(null) }) {
        Surface(
            modifier = Modifier.height(512.dp),
            elevation = 24.dp,
            shape = RoundedCornerShape(6.dp)
        ) {
            QrCamera(
                onSuccess = onDismiss,
                onError = {
                    Toast.makeText(context, R.string.camera_error, Toast.LENGTH_SHORT).show()
                    onDismiss(null)
                }
            )
        }
    }
}
