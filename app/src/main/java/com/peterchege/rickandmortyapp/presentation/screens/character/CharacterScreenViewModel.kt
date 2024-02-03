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

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter
import com.peterchege.rickandmortyapp.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository,
):ViewModel() {
    private val characterId = savedStateHandle.getStateFlow(key = "characterId", initialValue = "")

    private val _character = MutableStateFlow<SimpleCharacter?>(null)
    val character = _character.asStateFlow()



    init {
        getCharacterById()
    }

    private fun getCharacterById(){
        viewModelScope.launch {
            _character.update {
                characterRepository.getCharacterById(id = characterId.value)
            }
        }
    }
}