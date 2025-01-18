package com.hm.gymapp.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hm.gymapp.navigation.Router
import com.hm.gymapp.ui.theme.AppTheme


@Composable
fun BottomBar(bottomNavItems: List<BottomNavItem>, navController: NavController) {
    NavigationBar(
        containerColor = AppTheme.colorScheme.secondary,
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry.value?.destination?.parent?.route


        bottomNavItems.forEach { item ->
            val route = item.route.toString()
            NavigationBarItem(
                selected = currentRoute?.contains(route) ?: false,
                onClick = { navController.navigate(item) {
                    launchSingleTop = true
                    popUpTo(0)
                } },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}


data class BottomNavItem(
    val route: Router,
    val icon: ImageVector,
)