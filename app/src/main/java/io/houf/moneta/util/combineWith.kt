package io.houf.moneta.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, K> LiveData<T>.combineWith(liveData: LiveData<K>): LiveData<Pair<T?, K?>> {
    val result = MediatorLiveData<Pair<T?, K?>>()

    result.addSource(this) { result.value = Pair(value, liveData.value) }
    result.addSource(liveData) { result.value = Pair(value, liveData.value) }

    return result
}
