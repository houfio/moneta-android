package io.houf.moneta.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CurrenciesModel(
    val data: List<CurrencyModel>
)

@Parcelize
data class CurrencyModel(
    val id: Int,
    val name: String,
    val sign: String,
    val symbol: String
) : Parcelable
