package io.houf.moneta.util.intent

import android.content.Context
import android.content.Intent
import io.houf.moneta.R
import io.houf.moneta.util.formatNumber

fun sharePortfolio(
    context: Context,
    value: Double,
    sign: String,
    location: String?
) {
    val message = context.getString(
        R.string.share_message,
        value.formatNumber(start = sign),
        if (location != null) context.getString(R.string.share_location, location)
        else context.getString(R.string.share_location_fallback)
    )
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }

    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)))
}
