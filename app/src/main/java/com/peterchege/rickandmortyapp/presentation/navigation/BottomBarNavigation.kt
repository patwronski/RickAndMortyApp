/*
 * Copyright 2024 Rick And Morty App Peter Chege
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.rickandmortyapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.peterchege.rickandmortyapp.core.util.Screens
import com.peterchege.rickandmortyapp.domain.models.BottomNavItem
import com.peterchege.rickandmortyapp.presentation.screens.characters.AllCharacterScreen
import com.peterchege.rickandmortyapp.presentation.screens.episodes.AllEpisodesScreen
import com.peterchege.rickandmortyapp.presentation.screens.locations.AllLocationScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomBarNavigation(
    navController: NavHostController,
    navHostController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ALL_CHARACTERS_SCREEN
    ) {

        composable(
            route = Screens.ALL_CHARACTERS_SCREEN,
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ) {
            AllCharacterScreen(
                navigateToCharacterScreen = navHostController::navigateToCharacterScreen
            )
        }
        composable(
            route = Screens.ALL_EPISODE_SCREEN,
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ) {
            AllEpisodesScreen(
                navigateToEpisodeScreen = navHostController::navigateToEpisodeScreen
            )
        }
        composable(
            route = Screens.ALL_LOCATION_SCREEN,
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ) {
            AllLocationScreen(
                navigateToLocationScreen = navHostController::navigateToLocationScreen
            )
        }

    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    navHostController: NavHostController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name = "Characters",
                        route = Screens.ALL_CHARACTERS_SCREEN,
                        selectedIcon = Icons.Default.Home,
                        unselectedIcon = Icons.Outlined.Home,
                    ),
                    BottomNavItem(
                        name = "Episodes",
                        route = Screens.ALL_EPISODE_SCREEN,
                        selectedIcon = Icons.Default.Favorite,
                        unselectedIcon = Icons.Default.FavoriteBorder,

                        ),
                    BottomNavItem(
                        name = "Locations",
                        route = Screens.ALL_LOCATION_SCREEN,
                        selectedIcon = Icons.Default.Person,
                        unselectedIcon = Icons.Outlined.Person,
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route){
                        launchSingleTop = true

                    }
                }
            )
        }
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomBarNavigation(
                navController = navController,
                navHostController = navHostController
            )
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEachIndexed { index, item ->
            val isSelected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.name
                    )
                },
                label = { Text(text = item.name) },
                selected = isSelected,
                onClick = { onItemClick(item) }
            )
        }
    }
}