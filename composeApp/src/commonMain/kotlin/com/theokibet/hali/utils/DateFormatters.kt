package com.theokibet.hali.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun String.getDay(): String {
    val date = LocalDate.parse(this)
    return date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
}

fun String.getDayFromDateTime(): String {
    val date = LocalDateTime.parse(this)
    return date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
}

fun String.getDate(): String {
    val date = LocalDateTime.parse(this)
    return buildString {
        append(date.month.name.lowercase().replaceFirstChar { it.uppercase() })
        append(" ")
        append(date.dayOfMonth)
        append(" ")
        append(date.year)
    }
}

fun String.getTime(): String {
    val date = LocalDateTime.parse(this)
    return buildString {
        append(date.time)
    }
}
