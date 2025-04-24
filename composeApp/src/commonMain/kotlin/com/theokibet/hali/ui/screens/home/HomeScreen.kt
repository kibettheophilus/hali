package com.theokibet.hali.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = koinViewModel()) {
    val state by homeViewModel.homeUiState.collectAsState()

    when (state) {
        HomeUiState.Loading -> {

        }

        HomeUiState.Default -> {

        }

        is HomeUiState.Error -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text((state as HomeUiState.Error).errorMessage)
            }
        }

        is HomeUiState.Success -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("${(state as HomeUiState.Success).forecast}")
            }
        }
    }
}
