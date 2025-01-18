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
}