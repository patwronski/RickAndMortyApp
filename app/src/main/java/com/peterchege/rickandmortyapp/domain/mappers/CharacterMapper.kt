package com.peterchege.rickandmortyapp.domain.mappers

import com.peterchege.CharactersQuery
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter

fun CharactersQuery.Result.toSimpleCharacter():SimpleCharacter {
    return SimpleCharacter(
        name = name ?: "No Name",
        image = image ?: "No Image",
        status = status ?: "No Status",
        type = type ?: "No Type",
        gender = gender ?: "No Gender" ,

    )
}