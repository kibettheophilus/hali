package com.theokibet.hali.data.network.client

import com.theokibet.hali.utils.BASE_URL
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(
    engine: HttpClientEngine,
    enableLogs: Boolean,
) = HttpClient(engine) {
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            },
        )
    }

    install(Logging) {
        level = LogLevel.HEADERS
        logger =
            object : Logger {
                override fun log(message: String) {
                    if (enableLogs) Napier.i(tag = "Http Client", message = message)
                }
            }
    }.also {
        Napier.base(DebugAntilog())
    }

    install(DefaultRequest) {
        url {
            host = BASE_URL
            protocol = URLProtocol.HTTPS
        }
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}
