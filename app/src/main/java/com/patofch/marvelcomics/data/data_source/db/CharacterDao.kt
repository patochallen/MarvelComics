package com.patofch.marvelcomics.data.data_source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.patofch.marvelcomics.domain.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<Character>>

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: Int): Character?

    @Insert(onConflict = REPLACE)
    suspend fun insertCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)
}