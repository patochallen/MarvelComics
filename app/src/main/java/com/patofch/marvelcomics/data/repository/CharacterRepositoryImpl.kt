package com.patofch.marvelcomics.data.repository

import com.patofch.marvelcomics.data.data_source.db.CharacterDao
import com.patofch.marvelcomics.domain.model.Character
import com.patofch.marvelcomics.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl (
    private val characterDao: CharacterDao
) : CharacterRepository {
    override fun getCharacters(): Flow<List<Character>> = characterDao.getCharacters()

    override suspend fun getCharacterById(id: Int): Character? = characterDao.getCharacterById(id)

    override suspend fun insertCharacter(character: Character) = characterDao.insertCharacter(character)

    override suspend fun deleteCharacter(character: Character) = characterDao.deleteCharacter(character)
}