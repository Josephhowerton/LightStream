package com.square.lightstream.data
import androidx.lifecycle.LiveData
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.location.Location
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun getRMCharacters(callback: LoadCharactersCallback)
    suspend fun getRMCharacter(id: Int) : LiveData<RMCharacter>
    suspend fun getRMLocations(callback: LoadLocationsCallback)
    suspend fun getRMLocation(id: Int) : LiveData<Location>
    suspend fun saveRMCharacters(characters: List<RMCharacter>)
    suspend fun saveRMLocations(location: List<Location>)

    interface LoadCharactersCallback {
        suspend fun onDataLoaded(data: List<RMCharacter>)
        suspend fun onDataNotAvailable()
    }

    interface LoadLocationsCallback {
        suspend fun onDataLoaded(data: List<Location>)
        suspend fun onDataNotAvailable()
    }
}