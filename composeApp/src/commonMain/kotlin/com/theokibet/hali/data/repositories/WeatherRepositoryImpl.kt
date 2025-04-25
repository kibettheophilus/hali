package com.theokibet.hali.data.repositories

import com.theokibet.hali.data.mappers.mapDailyForecast
import com.theokibet.hali.data.mappers.mapHourlyForecast
import com.theokibet.hali.data.network.api.WeatherApi
import com.theokibet.hali.domain.models.DailyForecast
import com.theokibet.hali.domain.models.HourlyForecast
import com.theokibet.hali.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
    override suspend fun getDailyForecast(): Flow<Result<List<DailyForecast>>> {
        return try {
            val response = weatherApi.getDailyForecast().daily.mapDailyForecast()
            flowOf(Result.success(response))
        } catch (exception: Exception) {
            flowOf(Result.failure(exception))
        }
    }

    override suspend fun getHourlyForecast(date: String): Flow<Result<List<HourlyForecast>>> {
        return try {
            val response = weatherApi.getHourlyForecast(date = date).hourly.mapHourlyForecast()
            flowOf(Result.success(response))
        } catch (exception: Exception) {
            flowOf(Result.failure(exception))
        }
    }
}
