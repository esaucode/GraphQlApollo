package com.esaudev.graphqlapollo

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: RickAndMortyApi
): CharactersRepository {

    override suspend fun getCharacters(): Response<CharactersListQuery.Data> {
        return charactersApi.getApolloClient().query(CharactersListQuery()).await()
    }
}