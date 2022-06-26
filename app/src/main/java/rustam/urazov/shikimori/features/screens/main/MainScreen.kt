package rustam.urazov.shikimori.features.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import rustam.urazov.shikimori.features.MainActivityViewModel
import rustam.urazov.shikimori.features.screens.profile.Profile
import rustam.urazov.shikimori.features.screens.profile.ProfileViewModel
import rustam.urazov.shikimori.ui.theme.BlueStart
import rustam.urazov.shikimori.ui.theme.DarkGray
import rustam.urazov.shikimori.ui.theme.White

@Composable
fun Main(parentViewModel: MainActivityViewModel) {
    val items = listOf(Screen.Profile)

    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(backgroundColor = White) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(screen.icon),
                            contentDescription = null
                        )
                    },
                    label = { Text(screen.label) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    selectedContentColor = BlueStart,
                    unselectedContentColor = DarkGray,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Profile.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Profile.route) {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                Profile(profileViewModel, parentViewModel)
            }
        }
    }
}