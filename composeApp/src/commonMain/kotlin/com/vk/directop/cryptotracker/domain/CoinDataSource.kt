package com.vk.directop.cryptotracker.domain

import com.vk.directop.core.domain.util.NetworkError
import com.vk.directop.core.domain.util.Result
import kotlinx.datetime.Instant

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: Instant,
        end: Instant,
    ): Result<List<CoinPrice>, NetworkError>
}