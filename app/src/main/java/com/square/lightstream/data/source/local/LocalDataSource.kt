package com.square.lightstream.data.source.local

import androidx.lifecycle.LiveData
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.data.IDataSource
import com.square.lightstream.location.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val rmDao: RMDao): ILocalDataSource {

    override fun getRMCharacter(id: Int) : LiveData<RMCharacter> =
        rmDao.getRMCharacter(id)


    override suspend fun getRMCharacters(callback: IDataSource.LoadCharactersCallback) {
        val characters = rmDao.getRMCharacters()

        if( characters.isEmpty() ) {
            callback.onDataNotAvailable()
        }
        else {
            callback.onDataLoaded(characters)
        }
    }

    override fun getRMLocation(id: Int) : LiveData<Location> = rmDao.getRMLocation(id)

    override suspend fun getRMLocations(callback: IDataSource.LoadLocationsCallback) {
        val locations = rmDao.getRMLocations()

        if( locations.isEmpty() ) {
            callback.onDataNotAvailable()
        }
        else {
            callback.onDataLoaded(locations)
        }
    }

    override suspend fun saveRMCharacters(characters: List<RMCharacter>) {
        return rmDao.saveRMCharacters(characters)
    }

    override suspend fun saveRMLocations(location: List<Location>) {
        return rmDao.saveRMLocations(location)
    }
}