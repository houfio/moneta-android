package io.houf.moneta.util

import android.icu.text.NumberFormat

fun Double?.formatNumber(start: String = "", end: String = "") =
    if (this == null) "0.0" else "$start${NumberFormat.getNumberInstance().format(this)}$end"
