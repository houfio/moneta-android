package io.houf.moneta.util

import android.content.Context
import android.content.Intent
import android.os.Parcelable

fun share(
    context: Context,
    value: Double
) {
    val intent: Intent= Intent().apply {
        action = Intent.ACTION_SEND
        putExtra("value", value)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "Share!"))
}
