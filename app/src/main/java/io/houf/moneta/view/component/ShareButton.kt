package io.houf.moneta.view.component

import android.Manifest
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.houf.moneta.R
import io.houf.moneta.util.getLocation
import io.houf.moneta.util.sharePortfolio

@Composable
fun ShareButton(value: Double, sign: String) {
    val context = LocalContext.current

    val requestLocation = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        getLocation(context) { location ->
            sharePortfolio(
                context,
                value,
                sign,
                location
            )
        }
    }

    IconButton({
        requestLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }) {
        Icon(Icons.Outlined.Share, stringResource(R.string.share))
    }
}
