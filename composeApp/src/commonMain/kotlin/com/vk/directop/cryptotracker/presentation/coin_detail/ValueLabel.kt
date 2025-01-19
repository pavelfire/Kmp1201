package com.vk.directop.cryptotracker.presentation.coin_detail

import kotlin.math.pow
import kotlin.math.round

data class ValueLabel(
    val value: Float,
    val unit: String,
) {
    fun formatted(): String {
        val fractionDigits = when {
            value > 1000 -> 0
            value in 2f..999f -> 2
            else -> 3
        }
        val roundedValue = round(value * 10.0.pow(fractionDigits)) / 10.0.pow(fractionDigits)
        val formattedValue = roundedValue.toString()
        return "$formattedValue$unit"
    }
}
