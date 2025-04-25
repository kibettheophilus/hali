package com.theokibet.hali.domain.models

data class DailyForecast(
    val date: String,
    val weatherCode: Int,
    val maxTemp: Double,
    val minTemp: Double,
)
