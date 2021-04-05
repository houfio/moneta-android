package io.houf.moneta.util.data

import io.houf.moneta.storage.Portfolio

fun encodeListings(data: List<Portfolio>): String {
    return data.joinToString(",") { entry ->
        "${entry.id};${entry.amount.toBigDecimal().toPlainString()}"
    }
}
