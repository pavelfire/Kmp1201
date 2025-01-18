package com.vk.directop.cryptotracker.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank:Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double?,

    val supply: Double?,
    val maxSupply: Double?,
    val volumeUsd24Hr: Double?,
    val vwap24Hr: Double?,
)