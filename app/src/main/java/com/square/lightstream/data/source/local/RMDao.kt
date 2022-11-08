package com.square.lightstream.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.location.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface RMDao {
    @Query("SELECT * FROM rmcharacter")
    suspend fun getRMCharacters(): List<RMCharacter>

    @Query("SELECT * FROM rmcharacter where id=:id")
    fun getRMCharacter(id: Int): LiveData<RMCharacter>

    @Query("SELECT * FROM location")
    suspend fun getRMLocations(): List<Location>

    @Query("SELECT * FROM location where id=:id")
    fun getRMLocation(id: Int): LiveData<Location>

    @Insert
    suspend fun saveRMCharacters(characters: List<RMCharacter>)

    @Insert
    suspend fun saveRMLocations(location: List<Location>)

}