package io.houf.moneta.util

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun openActivity(context: Context, cls: Class<*>, bundle: Bundle = Bundle.EMPTY) {
    val intent = Intent(context, cls)

    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.putExtras(bundle)

    context.startActivity(intent)
}
