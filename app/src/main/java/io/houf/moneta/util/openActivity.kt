package io.houf.moneta.util

import android.content.Context
import android.content.Intent

fun openActivity(context: Context, cls: Class<*>) {
    val intent = Intent(context, cls)

    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    context.startActivity(intent)
}
