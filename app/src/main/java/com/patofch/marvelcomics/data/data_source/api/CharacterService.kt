package com.patofch.marvelcomics.data.data_source.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int? = 20,
        @Query("offset") offset: Int? = 0
    ): CharacterDataWrapper

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String
    ): CharacterDataWrapper

    @GET("/v1/public/characters")
    suspend fun getCharactersByName(
        @Query("nameStartsWith") name: String
    ): CharacterDataWrapper
}