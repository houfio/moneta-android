package io.houf.moneta.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue

class ViewModelFactory(
    private val queue: RequestQueue
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(queue::class.java)
            .newInstance(queue)
    }
}
