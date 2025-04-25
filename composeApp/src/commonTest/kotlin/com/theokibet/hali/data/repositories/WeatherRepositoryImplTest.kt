package com.theokibet.hali.data.repositories

import com.theokibet.hali.data.utils.dailyForecast
import com.theokibet.hali.data.utils.hourlyForecast
import com.theokibet.hali.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class WeatherRepositoryImplTest {
    private lateinit var repository: FakeWeatherRepository

    @BeforeTest
    fun setup() {
        repository = FakeWeatherRepository()
    }

    @Test
    fun `given weatherrepository - when getDailyForecast is success - returns a list of dailyforecast`() =
        runTest {
            val response = repository.getDailyForecast()
            assertEquals(dailyForecast, response.first().getOrNull())
            assertTrue(response.first().isSuccess)
            assertNotNull(response)
        }

    @Test
    fun `given weatherrepository - when getDailyForecast fails - returns an error`() =
        runTest {
            repository.shouldThroError = true
            val response = repository.getDailyForecast()
            assertEquals("Unknown error occurred", response.first().exceptionOrNull()?.message)
            assertTrue(response.first().isFailure)
            assertNotNull(response)
        }


    @Test
    fun `given weatherrepository - when getHourlyForecast is success - returns a list of hourlyforecast`() =
        runTest {
            val response = repository.getHourlyForecast("2025-05-14")
            assertEquals(hourlyForecast, response.first().getOrNull())
            assertTrue(response.first().isSuccess)
            assertNotNull(response)
        }

    @Test
    fun `given weatherrepository - when getHourlyForecast fails - returns an error`() =
        runTest {
            repository.shouldThroError = true
            val response = repository.getHourlyForecast("2025-05-14")
            assertEquals("Unknown error occurred", response.first().exceptionOrNull()?.message)
            assertTrue(response.first().isFailure)
            assertNotNull(response)
        }
}