package com.theokibet.hali.domain.models

data class HourlyForecast(
    val precipitation: Double,
    val temperatureM: Double,
    val time: String,
    val weatherCode: Int,
)
