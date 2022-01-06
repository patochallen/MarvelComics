package com.patofch.marvelcomics.domain.use_case.character

import com.patofch.marvelcomics.domain.model.Character
import com.patofch.marvelcomics.domain.repository.CharacterRepository

class DeleteCharacter(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(character: Character) = characterRepository.deleteCharacter(character)
}