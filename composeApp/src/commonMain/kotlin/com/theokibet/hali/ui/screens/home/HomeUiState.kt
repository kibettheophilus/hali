package com.theokibet.hali.ui.screens.home

import com.theokibet.hali.domain.models.DailyForecast

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(val dailyForecast: List<DailyForecast>) : HomeUiState

    data class Error(val errorMessage: String) : HomeUiState
}
