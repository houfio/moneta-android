package io.houf.moneta.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.houf.moneta.activity.FragmentActivity

fun <T> openActivity(context: Context, activity: FragmentActivity<T>, data: T) {
    val intent = Intent(context, activity::class.java)

    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.putExtras(Bundle().apply { activity.encode(this, data) })

    context.startActivity(intent)
}
