package io.houf.moneta.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListingsModel(
    val data: List<ListingModel>
)

@Parcelize
class ListingModel(
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
) : Parcelable {
    val q
        get() = quote.values.first()
}

@Parcelize
data class PlatformModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val tokenAddress: String
) : Parcelable

@Parcelize
class QuoteModel(
    val price: Double,
    @SerializedName("volume_24h") val volume24h: Double,
    @SerializedName("percent_change_1h") val percentChange1h: Double,
    @SerializedName("percent_change_24h") val percentChange24h: Double,
    @SerializedName("percent_change_7d") val percentChange7d: Double,
    val marketCap: Double,
    val lastUpdated: String
) : Parcelable {
    fun percentChange(range: String) = when (range) {
        "1h" -> percentChange1h
        "7d" -> percentChange7d
        else -> percentChange24h
    }
}
