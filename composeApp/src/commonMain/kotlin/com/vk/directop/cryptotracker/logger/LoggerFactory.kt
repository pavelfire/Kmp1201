package com.vk.directop.cryptotracker.logger

expect object LoggerFactory {
    fun create(): Logger
}