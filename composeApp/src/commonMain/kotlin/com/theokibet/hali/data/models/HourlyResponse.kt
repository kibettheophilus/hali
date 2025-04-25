package com.theokibet.hali.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyResponse(
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationtimeMs: Double,
    val hourly: Hourly,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
)

@Serializable
data class HourlyUnits(
    val precipitation: String,
    @SerialName("temperature_2m")
    val temperatureM: String,
    val time: String,
    val weathercode: String
)

@Serializable
data class Hourly(
    val precipitation: List<Double>,
    @SerialName("temperature_2m")
    val temperatureM: List<Double>,
    val time: List<String>,
    @SerialName("weathercode")
    val weatherCode: List<Int>,
)
