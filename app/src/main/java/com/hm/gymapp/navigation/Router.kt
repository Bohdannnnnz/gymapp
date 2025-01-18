package com.hm.gymapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Router {
    @Serializable
    data object Home : Router()
}