package com.theokibet.hali.data.network.api

import com.theokibet.hali.data.models.HourlyResponse
import com.theokibet.hali.data.network.client.createHttpClient
import com.theokibet.hali.data.utils.dailyResponse
import com.theokibet.hali.data.utils.hourlyResponse
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class WeatherApiTest {
    @Test
    fun `given weattherapi-when getDailyForecast is success-returns valid body of daily response`() =
        runTest {
            val apiCall = generateFakeApi(statusCode = HttpStatusCode.OK, response = dailyResponse)
            val response = apiCall.getDailyForecast()

            assertEquals("2025-04-24", response.daily.time.first())
        }

    @Test
    fun `given weattherapi-when getHourlyForecast is success-returns valid body of hourly response`() =
        runTest {
            val apiCall = generateFakeApi(statusCode = HttpStatusCode.OK, response = hourlyResponse)
            val response = apiCall.getHourlyForecast(date = "2025-04-24")

            assertEquals(Json.decodeFromString<HourlyResponse>(hourlyResponse), response)
        }

    private fun generateFakeApi(
        statusCode: HttpStatusCode,
        response: String,
    ) = WeatherApiImpl(
        httpClient =
            createHttpClient(
                engine =
                    generateFakeEngine(
                        statusCode = statusCode,
                        response = response,
                    ),
                enableLogs = false,
            ),
    )

    private fun generateFakeEngine(
        statusCode: HttpStatusCode,
        response: String,
    ) = MockEngine {
        val headers =
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
        respond(content = response, status = statusCode, headers = headers)
    }
}
