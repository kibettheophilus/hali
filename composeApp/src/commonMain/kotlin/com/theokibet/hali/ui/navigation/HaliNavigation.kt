package com.theokibet.hali.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.theokibet.hali.ui.screens.details.DetailScreen
import com.theokibet.hali.ui.screens.home.HomeScreen

@Composable
fun HaliNavigation(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(onClick = { date ->
                navController.navigate(Details(date = date))
            })
        }

        composable<Details> { navBackStackEntry ->
            val details: Details = navBackStackEntry.toRoute()
            DetailScreen(date = details.date)
        }
    }
}
