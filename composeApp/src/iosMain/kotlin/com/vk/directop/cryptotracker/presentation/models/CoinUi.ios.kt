package com.vk.directop.cryptotracker.presentation.models

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual class NumberFormatter actual constructor() {
    private val formatter = NSNumberFormatter().apply {
        minimumFractionDigits = 2u
        maximumFractionDigits = 2u
        numberStyle = NSNumberFormatterDecimalStyle
    }

    actual fun format(number: Double): String {
        return formatter.stringFromNumber(NSNumber(number)) ?: ""
    }
}