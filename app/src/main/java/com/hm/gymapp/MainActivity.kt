package com.hm.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hm.gymapp.navigation.Router
import com.hm.gymapp.navigation.getMainGraph
import com.hm.gymapp.ui.theme.GymAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GymAppTheme {

                val navController = rememberNavController()

                Scaffold(

                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Router.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        getMainGraph(
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}