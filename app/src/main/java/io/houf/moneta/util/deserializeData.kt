package io.houf.moneta.util

import io.houf.moneta.storage.Portfolio

fun deserializeData(data: String): List<Portfolio> {
    val result = mutableListOf<Portfolio>()
    val items = data.split(",")

    items.forEach { item ->
        val entry = item.split(";")

        if (entry.size != 2) {
            error("Invalid data format")
        }

        result.add(Portfolio(entry[0], entry[1].toDouble()))
    }

    return result.toList()
}
