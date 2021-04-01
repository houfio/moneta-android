package io.houf.moneta.util

import android.icu.text.NumberFormat

fun Double.formatNumber(start: String = "", end: String = "") =
    "$start${NumberFormat.getNumberInstance().format(this)}$end"
