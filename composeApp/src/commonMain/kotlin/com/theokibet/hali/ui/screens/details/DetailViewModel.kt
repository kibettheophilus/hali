package com.theokibet.hali.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theokibet.hali.domain.models.HourlyForecast
import com.theokibet.hali.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    fun getHourlyForecast(date: String) {
        viewModelScope.launch {
            weatherRepository.getHourlyForecast(date = date).collect { result ->
                result.onSuccess { forecast ->
                    _detailUiState.update {
                        DetailUiState.Success(
                            hourlyForecast = forecast,
                            selectedHour = forecast.first(),
                        )
                    }
                }.onFailure { error ->
                    _detailUiState.update {
                        DetailUiState.Error(errorMessage = error.message ?: "Uknown error occured")
                    }
                }
            }
        }
    }

    fun update(
        time: HourlyForecast,
        forecast: List<HourlyForecast>,
    ) {
        _detailUiState.update {
            DetailUiState.Success(
                hourlyForecast = forecast,
                selectedHour = time,
            )
        }
    }
}
