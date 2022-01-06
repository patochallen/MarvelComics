package com.patofch.marvelcomics.core.di

import android.app.Application
import androidx.room.Room
import com.patofch.marvelcomics.data.data_source.db.MarvelComicsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesMarvelComicsDatabase(app: Application): MarvelComicsDatabase {
        return Room.databaseBuilder(
            app,
            MarvelComicsDatabase::class.java,
            MarvelComicsDatabase.DATABASE_NAME
        ).build()
    }
}