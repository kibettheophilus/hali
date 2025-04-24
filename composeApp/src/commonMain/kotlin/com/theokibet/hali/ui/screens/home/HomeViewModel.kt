package com.theokibet.hali.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theokibet.hali.data.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Default)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        getDailyForecast()
    }

    fun getDailyForecast() {
        viewModelScope.launch {
            _homeUiState.update {
                HomeUiState.Loading
            }
            weatherRepository.getDailyForecast().collectLatest { result ->
                result.onSuccess { forecast ->
                    _homeUiState.update {
                        HomeUiState.Success(forecast = forecast)
                    }
                }.onFailure { error ->
                    HomeUiState.Error(errorMessage = error.message ?: "Unknown error occured")
                }
            }
        }
    }
}
