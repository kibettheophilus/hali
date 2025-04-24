package com.theokibet.hali.ui.screens.home

import com.theokibet.hali.data.models.DailyResponse

sealed interface HomeUiState {
    data object Default : HomeUiState

    data object Loading : HomeUiState

    data class Success(val forecast: DailyResponse) : HomeUiState

    data class Error(val errorMessage: String) : HomeUiState
}
