package com.theokibet.hali.utils

import hali.composeapp.generated.resources.Res
import hali.composeapp.generated.resources.cloudy
import hali.composeapp.generated.resources.drizzle
import hali.composeapp.generated.resources.fog
import hali.composeapp.generated.resources.rain
import hali.composeapp.generated.resources.snow
import hali.composeapp.generated.resources.sunny
import hali.composeapp.generated.resources.thunderstom
import org.jetbrains.compose.resources.DrawableResource

fun Int.getWeatherIcon(): DrawableResource {
    return when {
        this == 0 -> Res.drawable.sunny
        this in 1..3 -> Res.drawable.cloudy
        this in 45..48 -> Res.drawable.fog
        this in 51..57 -> Res.drawable.drizzle
        this in 61..67 -> Res.drawable.rain
        this in 71..77 -> Res.drawable.snow
        this in 80..86 -> Res.drawable.rain
        this in 95..99 -> Res.drawable.thunderstom
        else -> {
            Res.drawable.sunny
        }
    }
}
