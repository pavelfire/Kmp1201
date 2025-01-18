package com.vk.directop.cryptotracker.presentation.coin_list

import com.vk.directop.core.domain.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}