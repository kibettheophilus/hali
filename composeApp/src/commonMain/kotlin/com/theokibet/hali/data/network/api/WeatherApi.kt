package com.theokibet.hali.data.network.api

import com.theokibet.hali.data.models.DailyResponse
import com.theokibet.hali.data.models.HourlyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface WeatherApi {
    suspend fun getDailyForecast(): DailyResponse

    suspend fun getHourlyForecast(date: String): HourlyResponse
}

class WeatherApiImpl(private val httpClient: HttpClient) : WeatherApi {
    override suspend fun getDailyForecast(): DailyResponse {
        return httpClient.get("v1/forecast") {
            url {
                parameter("latitude", 35.68)
                parameter("longitude", 139.76)
                encodedParameters.append(
                    "daily",
                    "weathercode,temperature_2m_max,temperature_2m_min",
                )
                parameter("timezone", "Asia/Tokyo")
                parameter("forecast_days", 16)
            }
        }.body()
    }

    override suspend fun getHourlyForecast(date: String): HourlyResponse {
        return httpClient.get("v1/forecast") {
            url {
                parameter("latitude", 35.68)
                parameter("longitude", 139.76)
                encodedParameters.append("hourly", "temperature_2m,precipitation,weathercode")
                parameter("timezone", "Asia/Tokyo")
                parameter("start_date", date)
                parameter("end_date", date)
            }
        }.body()
    }
}
