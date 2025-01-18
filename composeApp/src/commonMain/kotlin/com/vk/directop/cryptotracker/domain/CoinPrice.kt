package com.vk.directop.cryptotracker.domain

import kotlinx.datetime.Instant

data class CoinPrice(
    val priceUsd: Double,
    val dateTime: Instant,
)
