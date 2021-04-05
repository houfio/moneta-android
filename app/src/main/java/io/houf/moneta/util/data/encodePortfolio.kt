package io.houf.moneta.util.data

import io.houf.moneta.storage.Portfolio

fun encodePortfolio(data: List<Portfolio>): String {
    return data.joinToString(",") { entry ->
        "${entry.id};${entry.amount.toBigDecimal().toPlainString()}"
    }
}
