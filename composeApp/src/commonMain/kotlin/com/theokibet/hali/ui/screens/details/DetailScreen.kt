package com.theokibet.hali.ui.screens.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.theokibet.hali.domain.models.HourlyForecast
import com.theokibet.hali.utils.getDate
import com.theokibet.hali.utils.getDayFromDateTime
import com.theokibet.hali.utils.getTime
import com.theokibet.hali.utils.getWeatherIcon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = koinViewModel(),
    date: String,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        detailViewModel.getHourlyForecast(date = date)
    }

    val detailUiState by detailViewModel.detailUiState.collectAsState()

    DetailScreenContent(
        detailUiState = detailUiState,
        onNavigateBack = onNavigateBack,
        onSelectHour = detailViewModel::update,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreenContent(
    detailUiState: DetailUiState,
    onNavigateBack: () -> Unit,
    onSelectHour: (time: HourlyForecast, forecast: List<HourlyForecast>) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                        )
                    }
                },
                title = {
                    Text(text = "")
                },
            )
        },
    ) {
        AnimatedContent(targetState = detailUiState) { result ->
            when (result) {
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
                        Text(result.errorMessage)
                    }
                }

                is DetailUiState.Success -> {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize(),
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = result.selectedHour.time.getDayFromDateTime(),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h5,
                            )
                            Text(
                                text = result.selectedHour.time.getDate(),
                            )
                        }
                        Column(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .size(250.dp),
                                painter = painterResource(result.selectedHour.weatherCode.getWeatherIcon()),
                                contentDescription = "",
                            )
                        }

                        LazyRow(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                        ) {
                            items(result.hourlyForecast) { item ->
                                Card(
                                    modifier =
                                        Modifier
                                            .padding(start = 16.dp),
                                    border =
                                        BorderStroke(
                                            width = if (result.selectedHour == item) 2.dp else 1.dp,
                                            color =
                                                if (result.selectedHour == item) {
                                                    MaterialTheme.colors.primary
                                                } else {
                                                    MaterialTheme.colors.onBackground.copy(
                                                        alpha = 0.25f,
                                                    )
                                                },
                                        ),
                                    shape = RoundedCornerShape(10),
                                    onClick = {
                                        onSelectHour(item, result.hourlyForecast)
                                    },
                                ) {
                                    Column(
                                        modifier =
                                            Modifier
                                                .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Text(
                                            text = item.time.getTime(),
                                        )
                                        Image(
                                            modifier =
                                                Modifier
                                                    .size(64.dp)
                                                    .padding(vertical = 8.dp),
                                            painter = painterResource(item.weatherCode.getWeatherIcon()),
                                            contentDescription = "",
                                        )
                                        Text(
                                            text =
                                                buildString {
                                                    append(item.temperatureM.toInt())
                                                    append("°C")
                                                },
                                            fontWeight = FontWeight.Bold,
                                            style = MaterialTheme.typography.h5,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
