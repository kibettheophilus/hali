package com.theokibet.hali.ui.screens.details

import com.theokibet.hali.domain.models.HourlyForecast

sealed interface DetailUiState {
    data object Loading : DetailUiState

    data class Error(val errorMessage: String) : DetailUiState

    data class Success(val hourlyForecast: List<HourlyForecast>, val selectedHour: HourlyForecast) :
        DetailUiState
}
