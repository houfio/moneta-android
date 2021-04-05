package io.houf.moneta.util

import io.houf.moneta.storage.Portfolio

fun serializeData(data: List<Portfolio>): String {
    return data.joinToString(",") { entry ->
        "${entry.id};${entry.amount.toBigDecimal().toPlainString()}"
    }
}
