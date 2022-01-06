package com.patofch.marvelcomics.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patofch.marvelcomics.domain.model.Character

@Database(
    entities = [
        Character::class
    ],
    version = 1
)
abstract class MarvelComicsDatabase: RoomDatabase() {

    abstract val characterDao: CharacterDao

    companion object {
        const val DATABASE_NAME = "marvel_comics_db"
    }
}