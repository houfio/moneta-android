package io.houf.moneta.view.component.portfolio

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.houf.moneta.R
import io.houf.moneta.view.component.camera.CameraDialog

@Composable
fun QrButton(importData: (String) -> Unit) {
    var open by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val requestCamera = registerForActivityResult(RequestPermission()) { success ->
        if (!success) {
            Toast.makeText(context, R.string.camera_error, Toast.LENGTH_SHORT).show()
        }

        open = success
    }

    if (open) {
        CameraDialog { result ->
            if (result != null) {
                importData(result)
            }

            open = false
        }
    }

    IconButton({
        requestCamera.launch(Manifest.permission.CAMERA)
    }) {
        Icon(Icons.Outlined.QrCode, stringResource(R.string.import_portfolio))
    }
}
