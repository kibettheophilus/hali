package com.theokibet.hali.data.repositories

import com.theokibet.hali.data.models.DailyResponse
import com.theokibet.hali.data.models.HourlyResponse
import com.theokibet.hali.data.network.api.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface WeatherRepository {
    suspend fun getDailyForecast(): Flow<Result<DailyResponse>>

    suspend fun getHourlyForecast(): Flow<Result<HourlyResponse>>
}

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
    override suspend fun getDailyForecast(): Flow<Result<DailyResponse>> {
        return try {
            val response = weatherApi.getDailyForecast()
            flowOf(Result.success(response))
        } catch (exception: Exception) {
            flowOf(Result.failure(exception))
        }
    }

    override suspend fun getHourlyForecast(): Flow<Result<HourlyResponse>> {
        return try {
            val response = weatherApi.getHourlyForecast()
            flowOf(Result.success(response))
        } catch (exception: Exception) {
            flowOf(Result.failure(exception))
        }
    }
}
