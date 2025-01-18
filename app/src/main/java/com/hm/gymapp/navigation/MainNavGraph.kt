package com.hm.gymapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hm.gymapp.BakingScreen

fun NavGraphBuilder.getMainGraph(
    navController: NavHostController
) {
    composable<Router.Home> {
        BakingScreen()
    }

    composable<Router.Welcome> {
        BakingScreen()
    }

    composable<Router.Survey> {
        BakingScreen()
    }
}


fun NavHostController.navigateTo(router: Router) {
    this.navigate(router) {
        launchSingleTop = true
    }
}