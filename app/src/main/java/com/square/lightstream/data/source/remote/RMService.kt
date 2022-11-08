package com.square.lightstream.data.source.remote

import com.square.lightstream.character.CharacterResponse
import com.square.lightstream.location.LocationResponse
import retrofit2.Response
import retrofit2.http.GET

interface RMService {

    @GET("/api/character")
    suspend fun fetchRMCharacters(): Response<CharacterResponse>

    @GET("/api/location")
    suspend fun fetchRMLocations(): Response<LocationResponse>
}