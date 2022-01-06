package com.patofch.marvelcomics.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val imageUrl: String
)
