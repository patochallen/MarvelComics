package com.patofch.marvelcomics.domain.use_case.character

data class CharacterUseCases(
    val getCharacters: GetCharacters,
    val getCharacterById: GetCharacterById,
    val insertCharacter: InsertCharacter,
    val deleteCharacter: DeleteCharacter
)
