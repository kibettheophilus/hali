package com.theokibet.hali

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.theokibet.hali.di.sharedModule
import com.theokibet.hali.ui.navigation.HaliNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.core.module.Module

@Composable
@Preview
fun App(
    platformModule: Module = Module()
) {
    val navController: NavHostController = rememberNavController()
    KoinApplication(
        application = {
            modules(listOf(sharedModule,platformModule))
        },
    ) {
        MaterialTheme {
            HaliNavigation(navController)
        }
    }
}
