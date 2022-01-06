package com.patofch.marvelcomics.core.di

import com.patofch.marvelcomics.data.data_source.api.CharacterService
import com.patofch.marvelcomics.data.data_source.db.MarvelComicsDatabase
import com.patofch.marvelcomics.data.repository.CharacterRepositoryImpl
import com.patofch.marvelcomics.domain.repository.CharacterRepository
import com.patofch.marvelcomics.domain.use_case.character.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun providesCharacterRepository(
        marvelComicsDatabase: MarvelComicsDatabase,
        characterService: CharacterService
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            characterDao = marvelComicsDatabase.characterDao,
            characterService = characterService
        )
    }

    @Provides
    @Singleton
    fun providesCharacterUseCases(
        characterRepository: CharacterRepository
    ): CharacterUseCases {
        return CharacterUseCases(
            getCharacters = GetCharacters(characterRepository),
            getCharacterById = GetCharacterById(characterRepository),
            insertCharacter = InsertCharacter(characterRepository),
            deleteCharacter = DeleteCharacter(characterRepository)
        )
    }
}