package com.peterchege.rickandmortyapp.data

import com.apollographql.apollo3.ApolloClient
import com.peterchege.CharactersQuery
import com.peterchege.rickandmortyapp.domain.mappers.toSimpleCharacter
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter
import com.peterchege.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
):CharacterRepository {

    override suspend fun getCharacters():List<SimpleCharacter?>? {
        return apolloClient.query(CharactersQuery())
            .execute()
            .data
            ?.characters
            ?.results
            ?.map {
                it?.toSimpleCharacter()
            }
    }
}