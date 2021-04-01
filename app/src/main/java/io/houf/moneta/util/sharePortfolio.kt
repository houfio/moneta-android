package io.houf.moneta.util

import android.content.Context
import android.content.Intent
import io.houf.moneta.R

fun sharePortfolio(
    context: Context,
    value: Double,
    sign: String
) {
    val message = context.getString(R.string.share_message, value.formatNumber(start = sign))
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }

    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)))
}
