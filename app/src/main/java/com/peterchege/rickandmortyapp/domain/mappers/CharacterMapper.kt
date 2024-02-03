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
package com.peterchege.rickandmortyapp.domain.mappers

import com.peterchege.CharacterQuery
import com.peterchege.CharactersQuery
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter

fun CharactersQuery.Result.toSimpleCharacter():SimpleCharacter {
    return SimpleCharacter(
        id = id ?:"",
        name = name ?: "No Name",
        image = image ?: "No Image",
        status = status ?: "No Status",
        type = type ?: "No Type",
        gender = gender ?: "No Gender" ,

    )
}

fun CharacterQuery.Character.toSimpleCharacter():SimpleCharacter {
    return SimpleCharacter(
        id = id ?:"",
        name = name ?: "No Name",
        image = image ?: "No Image",
        status = status ?: "No Status",
        type = type ?: "No Type",
        gender = gender ?: "No Gender" ,

        )
}