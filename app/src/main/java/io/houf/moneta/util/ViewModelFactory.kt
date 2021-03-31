package io.houf.moneta.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import java.lang.ref.WeakReference

class ViewModelFactory(
    private val context: WeakReference<Context>,
    private val queue: RequestQueue
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(context::class.java, queue::class.java)
            .newInstance(context, queue)
    }
}
