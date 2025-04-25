package com.theokibet.hali.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
data object Home

@Serializable
data class Details(val date: String)