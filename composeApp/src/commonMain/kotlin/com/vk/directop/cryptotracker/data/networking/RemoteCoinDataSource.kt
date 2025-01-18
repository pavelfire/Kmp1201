package com.vk.directop.cryptotracker.data.networking

import com.vk.directop.cryptotracker.core.data.constructUrl
import com.vk.directop.cryptotracker.core.data.safeCall
import com.vk.directop.cryptotracker.core.domain.NetworkError
import com.vk.directop.cryptotracker.core.domain.Result
import com.vk.directop.cryptotracker.core.domain.map
import com.vk.directop.cryptotracker.data.mappers.toCoin
import com.vk.directop.cryptotracker.data.mappers.toCoinPrice
import com.vk.directop.cryptotracker.data.networking.dto.CoinHistoryDto
import com.vk.directop.cryptotracker.data.networking.dto.CoinsResponseDto
import com.vk.directop.cryptotracker.domain.Coin
import com.vk.directop.cryptotracker.domain.CoinDataSource
import com.vk.directop.cryptotracker.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: LocalDateTime,
        end: LocalDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val timeZone = TimeZone.UTC
        val startMillis = start.toInstant(timeZone).toEpochMilliseconds()
        val endMillis = end.toInstant(timeZone).toEpochMilliseconds()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}