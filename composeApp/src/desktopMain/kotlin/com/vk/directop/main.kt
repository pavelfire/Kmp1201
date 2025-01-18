package com.vk.directop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vk.directop.cryptotracker.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kmp1201",
    ) {
        App()
    }
}