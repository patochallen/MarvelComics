package com.patofch.marvelcomics.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        DatabaseModule::class,
        CharacterModule::class
    ]
)
object AppModule {

}