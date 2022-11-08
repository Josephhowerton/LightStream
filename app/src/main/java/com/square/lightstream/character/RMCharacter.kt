package com.square.lightstream.character

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RMCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)