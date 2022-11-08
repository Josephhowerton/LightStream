package com.square.lightstream.data.source.local

import androidx.lifecycle.LiveData
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.data.IDataSource
import com.square.lightstream.location.Location
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    fun getRMCharacter(id: Int) : LiveData<RMCharacter>
    suspend fun getRMCharacters(callback: IDataSource.LoadCharactersCallback)
    suspend fun getRMLocations(callback: IDataSource.LoadLocationsCallback)
    fun getRMLocation(id: Int) : LiveData<Location>

    suspend fun saveRMCharacters(characters: List<RMCharacter>)
    suspend fun saveRMLocations(location: List<Location>)

}