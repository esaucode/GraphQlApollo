package com.esaudev.graphqlapollo

import com.apollographql.apollo.api.Response

interface CharactersRepository {

    suspend fun queryCharactersList(): Response<CharactersListQuery.Data>

}