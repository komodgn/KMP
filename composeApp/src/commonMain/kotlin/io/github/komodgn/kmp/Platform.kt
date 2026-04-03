package io.github.komodgn.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform