package com.vk.directop.cryptotracker

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.vk.directop.cryptotracker.presentation.coin_list.components.PriceChange
import com.vk.directop.cryptotracker.presentation.models.DisplayableNumber
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun PriceChangePreview() {
    MaterialTheme {
        PriceChange(
            change = DisplayableNumber(value = 2.43, formatted = "2.43")
        )
    }
}