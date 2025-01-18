package com.vk.directop.cryptotracker.presentation.coin_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.directop.core.domain.onError
import com.vk.directop.core.domain.onSuccess
import com.vk.directop.cryptotracker.domain.CoinDataSource
import com.vk.directop.cryptotracker.presentation.coin_detail.DataPoint
import com.vk.directop.cryptotracker.presentation.models.CoinUi
import com.vk.directop.cryptotracker.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlin.time.Duration.Companion.days


class CoinListViewModel(
    private val coinDataSource: CoinDataSource,
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                selectCoin(action.coinUi)
            }

            CoinListAction.OnRefresh -> loadCoins()
        }
    }

    private fun selectCoin(coinUi: CoinUi) {
        _state.update {
            it.copy(
                selectedCoin = coinUi
            )
        }

        viewModelScope.launch {
            coinDataSource.getCoinHistory(
                coinId = coinUi.id,
                start = Clock.System.now()
                    .minus(5.days)
                    .toLocalDateTime(TimeZone.currentSystemDefault()),
                end = Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault())
            )
                .onSuccess { history ->
                    val dataPoints = history
                        .sortedBy { it.dateTime }
                        .map {
                            DataPoint(
                                x = it.dateTime.hour.toFloat(),
                                y = it.priceUsd.toFloat(),
                                xLabel = formatLocalDateTime(it.dateTime)
                            )
                        }
                    _state.update {
                        it.copy(
                            selectedCoin = it.selectedCoin?.copy(
                                coinPriceHistory = dataPoints
                            ),
                        )
                    }
                }
                .onError { error ->
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUi() }
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    private fun formatLocalDateTime(dateTime: LocalDateTime): String {
        val hour = if (dateTime.hour % 12 == 0) 12 else dateTime.hour % 12
        val amPm = if (dateTime.hour < 12) "AM" else "PM"
        val month = dateTime.monthNumber
        val day = dateTime.dayOfMonth

        return "$hour$amPm\n$month/$day"
    }
}