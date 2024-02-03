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
package com.peterchege.rickandmortyapp.presentation.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter
import com.peterchege.rickandmortyapp.presentation.components.CharacterCard
import kotlinx.coroutines.flow.Flow

@Composable
fun AllCharacterScreen(
    viewModel: AllCharacterScreenViewModel = hiltViewModel(),
    navigateToCharacterScreen: (String) -> Unit,
) {
    val characters = viewModel.pagingCharacters.collectAsLazyPagingItems()

    AllCharactersScreenContent(
        characters = characters,
        navigateToCharacterScreen = navigateToCharacterScreen
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCharactersScreenContent(
    characters: LazyPagingItems<SimpleCharacter>,
    navigateToCharacterScreen: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Rick and Morty App")
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            items(count = characters.itemCount) { index ->
                val character = characters[index]
                if (character != null) {
                    CharacterCard(
                        character = character,
                        navigateToCharacterScreen = navigateToCharacterScreen
                    )
                }
            }
            item {
                if (characters.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }

                if (characters.loadState.prepend is LoadState.Loading) {
                    Text(text = "Loading Prepend")
                }
            }
        }


    }
}