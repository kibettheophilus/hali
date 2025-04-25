package com.theokibet.hali.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theokibet.hali.ui.components.DailyWeatherCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    onCardClicked: (String) -> Unit,
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()

    HomeScreenContent(
        homeUiState = homeUiState,
        onCardClicked = onCardClicked,
    )
}

@Composable
fun HomeScreenContent(
    homeUiState: HomeUiState,
    onCardClicked: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Hali")
                },
            )
        },
    ) { innerPadding ->
        AnimatedContent(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            targetState = homeUiState,
        ) { result ->
            when (result) {
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
                        Text(result.errorMessage)
                    }
                }

                is HomeUiState.Success -> {
                    LazyColumn {
                        items(result.dailyForecast) { item ->
                            DailyWeatherCard(
                                modifier =
                                    Modifier.fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .padding(top = 16.dp),
                                dailyForecast = item,
                                onCardClicked = onCardClicked,
                            )
                        }
                    }
                }
            }
        }
    }
}
