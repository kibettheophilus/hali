package com.theokibet.hali.data.repositories

import com.theokibet.hali.data.network.api.WeatherApi

interface WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository
