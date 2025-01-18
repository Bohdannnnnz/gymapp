package com.hm.gymapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Router {
    @Serializable
    data object Home : Router()

    @Serializable
    data object Welcome : Router()

    @Serializable
    data object Survey : Router()

    @Serializable
    data object Training : Router()

    @Serializable
    data object Settings : Router()

    @Serializable
    data object Schedule : Router()

    @Serializable
    data object Exercises : Router()

    @Serializable
    data object ExercisesDetails : Router()
}