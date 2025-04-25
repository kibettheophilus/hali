package com.theokibet.hali.domain.repositories

import com.theokibet.hali.domain.models.DailyForecast
import com.theokibet.hali.domain.models.HourlyForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getDailyForecast(): Flow<Result<List<DailyForecast>>>

    suspend fun getHourlyForecast(date: String): Flow<Result<List<HourlyForecast>>>
}
