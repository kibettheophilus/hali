package com.theokibet.hali.ui.screens.home

import com.theokibet.hali.data.repositories.FakeWeatherRepository
import com.theokibet.hali.data.utils.dailyForecast
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: FakeWeatherRepository

    @BeforeTest
    fun setup()  {
        repository = FakeWeatherRepository()
        viewModel =
            HomeViewModel(
                weatherRepository = repository,
            )
    }

    @Test
    fun `state is updated correctly when `() =
        runTest {
            viewModel.getDailyForecast()
            assertEquals(viewModel.homeUiState.value, HomeUiState.Success(dailyForecast = dailyForecast))
        }
}
