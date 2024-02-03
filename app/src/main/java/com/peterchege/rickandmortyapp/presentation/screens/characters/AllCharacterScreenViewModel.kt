package com.peterchege.rickandmortyapp.presentation.screens.characters

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
class AllCharacterScreenViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel(){

    private val _characters = MutableStateFlow<List<SimpleCharacter?>?>(emptyList())
    val characters = _characters.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(){
        viewModelScope.launch {
            val result = characterRepository.getCharacters()
            _characters.update { result }
        }
    }


}