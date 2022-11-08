package com.square.lightstream.character

data class CharacterResponse(
    val info: Info,
    val results: List<RMCharacter>
)