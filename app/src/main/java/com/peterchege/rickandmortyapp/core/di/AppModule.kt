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
package com.peterchege.rickandmortyapp.core.di

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.peterchege.rickandmortyapp.data.CharacterRepositoryImpl
import com.peterchege.rickandmortyapp.domain.models.SimpleCharacter
import com.peterchege.rickandmortyapp.domain.paging.AllCharactersPagingSource
import com.peterchege.rickandmortyapp.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }
    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context = context)
            .collector(ChuckerCollector(context = context))
            .maxContentLength(length = 250000L)
            .redactHeaders(headerNames = emptySet())
            .alwaysReadResponseBody(enable = false)
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(
        apolloClient: ApolloClient,
    ): CharacterRepository {
        return CharacterRepositoryImpl(apolloClient = apolloClient)
    }

    @Provides
    @Singleton
    fun provideAllCharactersPagingSource(
        characterRepository: CharacterRepository,
    ): Pager<Int, SimpleCharacter> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                AllCharactersPagingSource(characterRepository = characterRepository)
            }
        )
    }


}