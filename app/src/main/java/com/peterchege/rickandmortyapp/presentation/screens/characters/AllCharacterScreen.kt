package com.peterchege.rickandmortyapp.presentation.screens.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage

@Composable
fun AllCharacterScreen(
    viewModel: AllCharacterScreenViewModel = hiltViewModel()
) {
    val characters by viewModel.characters.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        characters?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(items = it) { character ->
                    character?.let {
                        Text(text = it.name)
                        Text(text = it.status)
                        SubcomposeAsyncImage(
                            model = it.image,
                            contentDescription = it.name,
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                    }

                }
            }
        } ?: Text(text = "No character found")


    }

}