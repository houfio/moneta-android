package io.houf.moneta.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.util.*

fun getLocation(context: Context, onSuccess: (String?) -> Unit) {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return onSuccess(null)
    }

    val locationService = LocationServices.getFusedLocationProviderClient(context)
    val geocoder = Geocoder(context, Locale.ENGLISH)

    locationService.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
        .addOnSuccessListener { location ->
            if (location == null) {
                return@addOnSuccessListener onSuccess(null)
            }

            val address =
                geocoder.getFromLocation(location.latitude, location.longitude, 1).firstOrNull()
                    ?: return@addOnSuccessListener onSuccess(null)

            onSuccess(address.locality)
        }.addOnCanceledListener {
        Log.d("moneta.share", "Failed to retrieve device location")

        onSuccess(null)
    }
}
