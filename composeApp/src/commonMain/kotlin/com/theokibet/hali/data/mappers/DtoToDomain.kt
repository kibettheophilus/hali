package com.theokibet.hali.data.mappers

import com.theokibet.hali.data.models.Daily
import com.theokibet.hali.data.models.Hourly
import com.theokibet.hali.domain.models.DailyForecast
import com.theokibet.hali.domain.models.HourlyForecast

fun Daily.mapDailyForecast(): List<DailyForecast> {
    return this.time.indices.map { index ->
        DailyForecast(
            date = this.time[index],
            weatherCode = this.weatherCode[index],
            maxTemp = this.temperatureMax[index],
            minTemp = this.temperatureMin[index],
        )
    }
}

fun Hourly.mapHourlyForecast(): List<HourlyForecast> {
    return this.time.indices.map { index ->
        HourlyForecast(
            precipitation = this.precipitation[index],
            temperatureM = this.temperatureM[index],
            time = this.time[index],
            weatherCode = this.weatherCode[index],
        )
    }
}
