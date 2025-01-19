package com.vk.directop.cryptotracker

import com.vk.directop.cryptotracker.logger.Logger

class DesctopLogger: Logger {
    override fun debug(
        message: String,
        tag: String
    ) {
        Log.d(tag, message)
    }

    override fun info(
        message: String,
        tag: String
    ) {
        Log.i(tag, message)
    }

    override fun warn(
        message: String,
        tag: String
    ) {
        Log.w(tag, message)
    }

    override fun error(
        message: String,
        tag: String,
        throwable: Throwable?
    ) {
        Log.e(tag, message, throwable)
    }
}