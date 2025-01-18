package com.vk.directop.cryptotracker.data.mappers

import com.vk.directop.cryptotracker.data.networking.dto.CoinDto
import com.vk.directop.cryptotracker.data.networking.dto.CoinPriceDto
import com.vk.directop.cryptotracker.domain.Coin
import com.vk.directop.cryptotracker.domain.CoinPrice
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24h = changePercent24Hr ?: 0.0,
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.fromEpochMilliseconds(time)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}
