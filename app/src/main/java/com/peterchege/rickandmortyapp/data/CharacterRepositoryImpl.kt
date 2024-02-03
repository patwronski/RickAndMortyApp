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
package com.peterchege.rickandmortyapp.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.peterchege.CharacterQuery
import com.peterchege.CharactersQuery
import com.peterchege.rickandmortyapp.domain.mappers.toSimpleCharacter
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter
import com.peterchege.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
):CharacterRepository {

    override suspend fun getCharacters(page:Int):List<SimpleCharacter> {
        return apolloClient
            .query(CharactersQuery(page = Optional.present(page)))
            .execute()
            .data
            ?.characters
            ?.results
            ?.filterNotNull()
            ?.map {
                it.toSimpleCharacter()
            } ?: emptyList()
    }

    override suspend fun getCharacterById(id: String): SimpleCharacter? {
        return apolloClient.query(CharacterQuery(id))
            .execute()
            .data
            ?.character
            ?.toSimpleCharacter()

    }
}