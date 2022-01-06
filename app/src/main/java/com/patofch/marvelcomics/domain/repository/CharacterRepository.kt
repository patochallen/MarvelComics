package com.patofch.marvelcomics.domain.repository

import com.patofch.marvelcomics.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(): Flow<List<Character>>

    suspend fun getCharacterById(id: Int): Character?

    suspend fun insertCharacter(character: Character)

    suspend fun deleteCharacter(character: Character)
}