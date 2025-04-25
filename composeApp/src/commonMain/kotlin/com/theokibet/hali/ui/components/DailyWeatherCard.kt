package com.theokibet.hali.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.theokibet.hali.domain.models.DailyForecast
import com.theokibet.hali.utils.getDay
import com.theokibet.hali.utils.getWeatherIcon
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DailyWeatherCard(
    modifier: Modifier = Modifier,
    dailyForecast: DailyForecast,
    onCardClicked: (String) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = { onCardClicked(dailyForecast.date) },
        border =
            BorderStroke(
                width = 1.dp,
                color =
                    MaterialTheme.colors.onBackground.copy(
                        alpha = 0.25f,
                    ),
            ),
        shape = RoundedCornerShape(10),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                modifier =
                    Modifier
                        .size(width = 100.dp, height = 100.dp)
                        .padding(16.dp),
                painter = painterResource(dailyForecast.weatherCode.getWeatherIcon()),
                contentDescription = "",
            )

            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = dailyForecast.date.getDay(),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = dailyForecast.date,
                )
            }

            Column(
                modifier = Modifier.padding(end = 16.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text =
                        buildString {
                            append(dailyForecast.maxTemp.toInt())
                            append("°C")
                        },
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "-",
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text =
                        buildString {
                            append(dailyForecast.minTemp.toInt())
                            append("°C")
                        },
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
