package com.vk.directop.cryptotracker.logger

import platform.Foundation.NSLog

class IOSLogger : Logger {
    override fun debug(
        message: String,
        tag: String
    ) {
        NSLog("DEBUG: $tag - $message")
    }

    override fun info(
        message: String,
        tag: String
    ) {
        NSLog("INFO: $tag - $message")
    }

    override fun warn(
        message: String,
        tag: String
    ) {
        NSLog("WARN: $tag - $message")
    }

    override fun error(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        NSLog("ERROR: $tag - $message. Throwable: ${throwable?.message}")
    }
}