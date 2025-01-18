package com.vk.directop.cryptotracker

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object CoinList: Route

    @Serializable
    data class CoinDetail(val id: String): Route
}