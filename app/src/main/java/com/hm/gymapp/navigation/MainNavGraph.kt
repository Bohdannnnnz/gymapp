package com.hm.gymapp.navigation

import ExerciseDetailScreen
import ExercisesScreen
import ScheduleScreen
import SettingsScreen
import TrainingScreen
import WelcomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hm.gymapp.BakingScreen
import com.hm.gymapp.ui.home.HomeScreen
import com.hm.gymapp.ui.survey.SurveyScreen

fun NavGraphBuilder.getMainGraph(
    navController: NavHostController,
    isFirstTime: Boolean
) {
    composable<Router.Home> {
        HomeScreen()
    }

    composable<Router.Welcome> {
        WelcomeScreen(
            onLogin = {
                if (isFirstTime) {
                    navController.navigateTo(Router.Survey)
                } else {
                    navController.navigateTo(Router.Home)
                }
            },
        )
    }

    composable<Router.Survey> {
        SurveyScreen(
            onFinished = {
                navController.navigateTo(Router.Home)
            }
        )
    }

    composable<Router.Settings> {
        SettingsScreen()
    }

    composable<Router.Schedule> {
        ScheduleScreen()
    }

    composable<Router.Training> {
        TrainingScreen(
            onClick = { navController.navigateTo(Router.Exercises) }
        )
    }

    composable<Router.Exercises> {
        ExercisesScreen(
            onClick = { navController.navigateTo(Router.ExercisesDetails) },
            onBack = { navController.popBackStack() }
        )
    }

    composable<Router.ExercisesDetails> {
        ExerciseDetailScreen(onBack = { navController.popBackStack() })
    }


}


fun NavHostController.navigateTo(router: Router) {
    this.navigate(router) {
        launchSingleTop = true
    }
}