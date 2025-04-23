package com.theokibet.hali

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform