package io.houf.moneta.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>, consume: (T?) -> Boolean) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            if (consume(t)) {
                observer.onChanged(t)
                removeObserver(this)
            }
        }
    })
}
