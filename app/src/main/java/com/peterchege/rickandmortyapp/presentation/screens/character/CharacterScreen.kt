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
package com.peterchege.rickandmortyapp.presentation.screens.character

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.peterchege.rickandmortyapp.core.util.calculateDoubleTapOffset
import com.peterchege.rickandmortyapp.core.util.calculateNewOffset
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter

@Composable
fun CharacterScreen(
    viewModel: CharacterScreenViewModel = hiltViewModel()
) {
    val character by viewModel.character.collectAsStateWithLifecycle()
    
    CharacterScreenContent(character = character)

}


@Composable
fun CharacterScreenContent(
    character:SimpleCharacter?
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        if (character != null){
            Column (
                modifier = Modifier.fillMaxSize()
            ){
                var offset by remember { mutableStateOf(Offset.Zero) }
                var zoom by remember { mutableStateOf(1f) }
                SubcomposeAsyncImage(
                    model = character.image,
                    loading = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clipToBounds()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = { tapOffset ->
                                    zoom = if (zoom > 1f) 1f else 2f
                                    offset = calculateDoubleTapOffset(
                                        zoom = zoom,
                                        size = size,
                                        tapOffset = tapOffset
                                    )

                                }
                            )
                        }
                        .pointerInput(Unit) {
                            detectTransformGestures(
                                onGesture = { centroid, pan, gestureZoom, _ ->
                                    offset = offset.calculateNewOffset(
                                        centroid = centroid,
                                        pan = pan,
                                        zoom = zoom,
                                        gestureZoom = gestureZoom,
                                        size = size
                                    )
                                    zoom = maxOf(a = 1f, b = zoom * gestureZoom)
                                }
                            )
                        }
                        .graphicsLayer {
                            translationX = -offset.x * zoom
                            translationY = -offset.y * zoom
                            scaleX = zoom
                            scaleY = zoom
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0f,
                                pivotFractionY = 0f
                            )
                        },
                    contentDescription = null
                )

                Text(text = character.name)
                Text(text = character.status)
                Text(text = character.type)
                Text(text = character.gender)
            }
        }


    }

}