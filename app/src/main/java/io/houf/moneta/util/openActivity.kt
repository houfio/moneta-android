package io.houf.moneta.util

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import io.houf.moneta.activity.FragmentActivity

fun <T : Parcelable> openActivity(context: Context, activity: Class<out FragmentActivity<T>>, data: T) {
    val intent = Intent(context, activity)

    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.putExtra("data", data)

    context.startActivity(intent)
}
