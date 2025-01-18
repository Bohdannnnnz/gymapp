package com.hm.gymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hm.gymapp.data.service.LocalRepository
import com.hm.gymapp.navigation.Router
import com.hm.gymapp.navigation.getMainGraph
import com.hm.gymapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localRepo: LocalRepository

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

                Scaffold(

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