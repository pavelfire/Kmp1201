package com.vk.directop.cryptotracker.logger

actual object LoggerFactory {
    actual fun create(): Logger = IOSLogger()
}