package io.houf.moneta.model

data class ListingResponseModel(
    val data: List<ListingModel>
)

data class ListingModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val cmcRank: Int,
    val numMarketPairs: Int,
    val circulatingSupply: Double,
    val totalSupply: Double,
    val maxSupply: Double?,
    val lastUpdated: String,
    val dateAdded: String,
    val tags: List<String>,
    val platform: PlatformModel?,
    val quote: Map<String, QuoteModel>
)

data class PlatformModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val tokenAddress: String
)

data class QuoteModel(
    val price: Double,
    val volume24H: Double,
    val percentChange1H: Double,
    val percentChange24H: Double,
    val percentChange7D: Double,
    val marketCap: Double,
    val lastUpdated: String
)
