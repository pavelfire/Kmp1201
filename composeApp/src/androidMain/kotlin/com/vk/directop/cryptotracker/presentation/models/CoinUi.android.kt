package com.vk.directop.cryptotracker.presentation.models

actual class NumberFormatter actual constructor() {
    private val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }

    actual fun format(number: Double): String {
        return formatter.format(number)
    }
}