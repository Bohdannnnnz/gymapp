package com.hm.gymapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hm.gymapp.data.service.LocalRepository
import com.hm.gymapp.navigation.Router
import com.hm.gymapp.navigation.getMainGraph
import com.hm.gymapp.ui.component.BottomBar
import com.hm.gymapp.ui.component.BottomNavItem
import com.hm.gymapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localRepo: LocalRepository


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isFirstLogin = localRepo.getData("isFirstLogin")?.toBoolean() ?: true

        if (!isFirstLogin) {
            localRepo.saveData("isFirstLogin", "true")
        }



        setContent {
            AppTheme {

                val navController = rememberNavController()
                val topLevelRoutes = listOf(
                    TopLevelRoute("Home", Router.Home, Icons.Rounded.Home),
                    TopLevelRoute("Trainig", Router.Training, ImageVector.vectorResource(id = R.drawable.fitness_ic)),
                    TopLevelRoute("Schedule", Router.Schedule, Icons.Rounded.DateRange),
                    TopLevelRoute("Settings", Router.Settings, Icons.Rounded.Settings),
                )


                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            topLevelRoutes.forEach { topLevelRoute ->
                                NavigationBarItem(
                                    icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) },
                                    label = { Text(topLevelRoute.name) },
                                    selected = currentDestination?.hierarchy?.any { it.hasRoute(
                                        topLevelRoute.route::class.toString(),
                                        arguments = Bundle.EMPTY
                                    ) } == true,
                                    onClick = {
                                        navController.navigate(topLevelRoute.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Router.Welcome,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        getMainGraph(
                            navController = navController,
                            isFirstTime = isFirstLogin
                        )
                    }
                }
            }
        }
    }
}

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)