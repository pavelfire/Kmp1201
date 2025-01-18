package com.vk.directop.cryptotracker.domain

import com.vk.directop.cryptotracker.core.domain.NetworkError
import com.vk.directop.cryptotracker.core.domain.Result
import kotlinx.datetime.LocalDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: LocalDateTime,
        end: LocalDateTime,
    ): Result<List<CoinPrice>, NetworkError>
}