package io.houf.moneta.util.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, K> LiveData<T>.combineWith(liveData: LiveData<K>): LiveData<Pair<T?, K?>> {
    val result = MediatorLiveData<Pair<T?, K?>>()

    result.addSource(this) { result.value = Pair(value, liveData.value) }
    result.addSource(liveData) { result.value = Pair(value, liveData.value) }

    return result
}

fun <T, K, V> LiveData<T>.combineWith(liveData: LiveData<K>, liveDataExtra: LiveData<V>): LiveData<Triple<T?, K?, V?>> {
    val result = MediatorLiveData<Triple<T?, K?, V?>>()

    result.addSource(this) { result.value = Triple(value, liveData.value, liveDataExtra.value) }
    result.addSource(liveData) { result.value = Triple(value, liveData.value, liveDataExtra.value) }
    result.addSource(liveDataExtra) { result.value = Triple(value, liveData.value, liveDataExtra.value) }

    return result
}
