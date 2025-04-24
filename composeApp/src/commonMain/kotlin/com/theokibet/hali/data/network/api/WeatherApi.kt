package com.theokibet.hali.data.network.api

import com.theokibet.hali.data.models.DailyResponse
import com.theokibet.hali.data.models.HourlyResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface WeatherApi {
    suspend fun getDailyForecast(): DailyResponse
    suspend fun getHourlyForecast(): HourlyResponse
}

class WeatherApiImpl(private val httpClient: HttpClient) : WeatherApi {
    override suspend fun getDailyForecast(): DailyResponse {
        return httpClient.get {
            parameter("latitude", 35.68)
            parameter("longintude", 139.76)
            parameter("daily", "weathercode,temperature_2m_max,temperature_2m_min")
            parameter("timezone", "Asia%2FTokyo")
            parameter("forecast_days", 8)
        }.body()
    }

    override suspend fun getHourlyForecast(): HourlyResponse {
        return httpClient.get {
            parameter("latitude", 35.68)
            parameter("longintude", 139.76)
            parameter("hourly", "temperature_2m,precipitation")
            parameter("timezone", "Asia%2FTokyo")
        }.body()
    }
}