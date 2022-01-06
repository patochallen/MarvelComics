package com.patofch.marvelcomics.data.data_source.api

import com.patofch.marvelcomics.domain.model.Character

data class CharacterDataWrapper(
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val status: String
) {

    data class Data(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<CharacterApiEntity>,
        val total: Int
    )

    data class CharacterApiEntity(
        val comics: Content,
        val description: String,
        val events: Content,
        val id: Int,
        val modified: String,
        val name: String,
        val resourceURI: String,
        val series: Content,
        val stories: Content,
        val thumbnail: Thumbnail,
    ) {
        fun toCharacter(): Character {
            return Character(
                name = name,
                imageUrl = "${thumbnail.path}.${thumbnail.extension}"
            )
        }
    }

    data class Content(
        val available: Int,
        val collectionURI: String,
        val items: List<Item>,
        val returned: Int
    )

    data class Item(
        val name: String,
        val resourceURI: String
    )

    data class Thumbnail(
        val extension: String,
        val path: String
    )
}