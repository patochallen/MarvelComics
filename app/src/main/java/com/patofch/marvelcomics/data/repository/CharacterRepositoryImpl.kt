package com.patofch.marvelcomics.data.repository

import com.patofch.marvelcomics.data.data_source.api.CharacterService
import com.patofch.marvelcomics.data.data_source.db.CharacterDao
import com.patofch.marvelcomics.domain.model.Character
import com.patofch.marvelcomics.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepositoryImpl (
    private val characterDao: CharacterDao,
    private val characterService: CharacterService
) : CharacterRepository {
    override fun getCharacters(): Flow<List<Character>> {
        return flow {
            val response = characterService.getCharacters()
            emit(response.data.results.map { it.toCharacter()})
        }.flowOn(Dispatchers.IO)
    }//= characterDao.getCharacters()

    override suspend fun getCharacterById(id: Int): Character? = characterDao.getCharacterById(id)

    override suspend fun insertCharacter(character: Character) = characterDao.insertCharacter(character)

    override suspend fun deleteCharacter(character: Character) = characterDao.deleteCharacter(character)
}