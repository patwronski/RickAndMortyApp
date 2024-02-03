package com.peterchege.rickandmortyapp.domain.repository

import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter

interface CharacterRepository {

    suspend fun getCharacters() :List<SimpleCharacter?>?
}