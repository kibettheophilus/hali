package com.theokibet.hali

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.theokibet.hali.di.sharedModule
import com.theokibet.hali.ui.navigation.HaliNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(sharedModule)
        },
    ) {
        MaterialTheme {
            HaliNavigation()
        }
    }
}
