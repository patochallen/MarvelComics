package com.patofch.marvelcomics.domain.use_case.character

import com.patofch.marvelcomics.domain.model.Character
import com.patofch.marvelcomics.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacters(
    private val characterRepository: CharacterRepository
) {

    operator fun invoke(): Flow<List<Character>> = characterRepository.getCharacters()
}