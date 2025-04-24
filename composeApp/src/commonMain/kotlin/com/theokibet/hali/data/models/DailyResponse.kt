package com.theokibet.hali.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyResponse(
    val daily: Daily,
    @SerialName("daily_units")
    val dailyUnits: DailyUnits,
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)

@Serializable
data class DailyUnits(
    @SerialName("temperature_2m_max")
    val temperatureMax: String,
    @SerialName("temperature_2m_min")
    val temperatureMin: String,
    val time: String,
    @SerialName("weathercode")
    val weatherCode: String
)

@Serializable
data class Daily(
    @SerialName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerialName("temperature_2m_min")
    val temperatureMin: List<Double>,
    val time: List<String>,
    @SerialName("weathercode")
    val weatherCode: List<Int>
)