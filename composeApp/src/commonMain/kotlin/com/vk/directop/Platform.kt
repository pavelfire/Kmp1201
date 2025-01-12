package com.vk.directop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform