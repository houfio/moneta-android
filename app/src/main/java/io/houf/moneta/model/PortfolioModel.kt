package io.houf.moneta.model

import io.houf.moneta.storage.Portfolio

data class PortfolioModel(
    val listing: ListingModel,
    val portfolio: Portfolio
)
