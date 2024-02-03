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
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.rickandmortyapp.core.util.Screens
import com.peterchege.rickandmortyapp.presentation.screens.character.CharacterScreen
import com.peterchege.rickandmortyapp.presentation.screens.episode.EpisodeScreen
import com.peterchege.rickandmortyapp.presentation.screens.location.LocationScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController:NavHostController) {

    NavHost(
        navController =navController,
        startDestination = Screens.BOTTOM_BAR_NAVIGATION,
    ){
        composable(
            route = Screens.BOTTOM_BAR_NAVIGATION,
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ){
            DashBoardScreen(navHostController = navController)
        }
        composable(
            route = Screens.CHARACTER_SCREEN + "/{characterId}",
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ){
            CharacterScreen()
        }
        composable(
            route = Screens.EPISODE_SCREEN + "/{episodeId}",
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ){
            EpisodeScreen()
        }

        composable(
            route = Screens.LOCATION_SCREEN + "/{locationId}",
            enterTransition = { scaleInEnterTransition() },
            exitTransition = { scaleOutExitTransition() },
            popEnterTransition = { scaleInPopEnterTransition() },
            popExitTransition = { scaleOutPopExitTransition() }
        ){
            LocationScreen()
        }

    }

}