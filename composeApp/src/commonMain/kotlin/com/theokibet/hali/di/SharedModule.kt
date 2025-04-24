package com.theokibet.hali.di

import com.theokibet.hali.data.network.api.WeatherApi
import com.theokibet.hali.data.network.api.WeatherApiImpl
import com.theokibet.hali.data.network.client.createHttpClient
import com.theokibet.hali.data.repositories.WeatherRepository
import com.theokibet.hali.data.repositories.WeatherRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule = module {
    single { createHttpClient(engine = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(weatherApi = get()) }
    single<WeatherApi> { WeatherApiImpl(httpClient = get()) }
}

expect fun platformModule(): Module

