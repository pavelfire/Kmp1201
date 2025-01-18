package com.vk.directop

import androidx.compose.ui.window.ComposeUIViewController
import com.vk.directop.cryptotracker.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }