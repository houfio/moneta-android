package io.houf.moneta.local

import androidx.compose.runtime.compositionLocalOf
import com.android.volley.RequestQueue

val LocalQueue = compositionLocalOf<RequestQueue> { error("No queue") }
