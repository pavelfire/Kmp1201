package com.vk.directop.cryptotracker.presentation.coin_list

import com.vk.directop.cryptotracker.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
    data object OnRefresh : CoinListAction
}