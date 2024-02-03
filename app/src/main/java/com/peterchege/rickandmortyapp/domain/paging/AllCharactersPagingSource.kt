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
package com.peterchege.rickandmortyapp.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter
import com.peterchege.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class AllCharactersPagingSource @Inject constructor(
    private val characterRepository: CharacterRepository,
) : PagingSource<Int, SimpleCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleCharacter> {
        val pageNumber = params.key ?: 1
        val characters = characterRepository.getCharacters(pageNumber)
        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = pageNumber + 1
        return LoadResult.Page(
            data = characters,
            nextKey = nextKey,
            prevKey = prevKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, SimpleCharacter>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}