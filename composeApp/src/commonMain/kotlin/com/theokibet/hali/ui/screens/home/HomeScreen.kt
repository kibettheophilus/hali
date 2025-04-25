package com.theokibet.hali.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel(),
    onClick: (String) -> Unit
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    HomeScreenContent(homeUiState = homeUiState, onClick = onClick)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    onClick: (String) -> Unit
) {
    when (homeUiState) {
        HomeUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeUiState.Error -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(homeUiState.errorMessage)
            }
        }

        is HomeUiState.Success -> {
            LazyColumn(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(homeUiState.dailyForecast) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onClick = { onClick(item.date) },
                        backgroundColor = Color.Gray
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(item.date)
                            Text(item.weatherCode.toString())
                            Text(item.maxTemp.toString())
                            Text(item.minTemp.toString())
                        }
                    }
                }
            }
        }
    }
}
