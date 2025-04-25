package com.theokibet.hali.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = koinViewModel(),
    date: String
) {
    LaunchedEffect(key1 = true) {
        detailViewModel.getHourlyForecast(date = date)
    }

    val detailUiState by detailViewModel.detailUiState.collectAsState()

    DetailScreenContent(detailUiState = detailUiState)
}


@Composable
fun DetailScreenContent(modifier: Modifier = Modifier, detailUiState: DetailUiState) {
    when (detailUiState) {
        DetailUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }

        is DetailUiState.Error -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(detailUiState.errorMessage)
            }
        }

        is DetailUiState.Success -> {
            LazyColumn {
                items(detailUiState.hourlyForecast) { item ->
                    Text(item.time)
                }
            }
        }
    }
}
