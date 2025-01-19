package com.vk.directop.cryptotracker.logger

interface Logger {
    fun debug(
        message: String,
        tag: String = MY_TAG
    )

    fun info(
        message: String,
        tag: String = MY_TAG
    )

    fun warn(
        message: String,
        tag: String = MY_TAG
    )

    fun error(
        message: String,
        tag: String = MY_TAG,
        throwable: Throwable? = null
    )
}

const val MY_TAG = "MY_TAG"