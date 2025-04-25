package com.theokibet.hali.data.repositories

import com.theokibet.hali.data.utils.dailyForecast
import com.theokibet.hali.data.utils.hourlyForecast
import com.theokibet.hali.domain.models.DailyForecast
import com.theokibet.hali.domain.models.HourlyForecast
import com.theokibet.hali.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeWeatherRepository : WeatherRepository {
    var shouldThroError = false
    override suspend fun getDailyForecast(): Flow<Result<List<DailyForecast>>> {
        return if (shouldThroError) {
            flowOf(Result.failure(exception = Exception("Unknown error occurred")))
        } else flowOf(Result.success(dailyForecast))
    }

    override suspend fun getHourlyForecast(date: String): Flow<Result<List<HourlyForecast>>> {
        return if (shouldThroError) {
            flowOf(Result.failure(exception = Exception("Unknown error occurred")))
        } else flowOf(Result.success(hourlyForecast))
    }
}

