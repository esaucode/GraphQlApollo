package com.esaudev.graphqlapollo

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val webService: RickAndMortyApi
) : CharactersRepository {

    override suspend fun queryCharactersList(): Response<CharactersListQuery.Data> {
        return webService.getApolloClient().query(CharactersListQuery()).await()
    }

}