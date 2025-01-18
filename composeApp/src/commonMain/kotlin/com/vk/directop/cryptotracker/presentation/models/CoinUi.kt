package com.vk.directop.cryptotracker.presentation.models

import com.vk.directop.cryptotracker.core.presentation.getDrawableIdForCoin
import com.vk.directop.cryptotracker.domain.Coin
import com.vk.directop.cryptotracker.presentation.coin_detail.DataPoint
import org.jetbrains.compose.resources.DrawableResource

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24h: DisplayableNumber,
    val iconRes: DrawableResource,
    val coinPriceHistory: List<DataPoint> = emptyList(),
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String,
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        changePercent24h = changePercent24h.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormatter()
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

expect class NumberFormatter() {
    fun format(number: Double): String
}