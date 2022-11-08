package com.square.lightstream.data

import androidx.lifecycle.LiveData
import com.square.lightstream.location.Location
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.data.source.local.ILocalDataSource
import com.square.lightstream.data.source.remote.IRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: IRemoteDataSource,
                                     private val localDataSource: ILocalDataSource
) : IDataSource {

    private val mCachedCharacters: MutableList<RMCharacter> = mutableListOf()
    private val mCachedLocation: MutableList<Location> = mutableListOf()

    override suspend fun getRMCharacters(callback: IDataSource.LoadCharactersCallback) {
        return withContext(Dispatchers.IO) {

            if(mCachedCharacters.isNotEmpty()) {
                callback.onDataLoaded(mCachedCharacters)
                return@withContext
            }

            localDataSource.getRMCharacters(object: IDataSource.LoadCharactersCallback {
                override suspend fun onDataLoaded(data: List<RMCharacter>) {
                    updateCharactersCache(data)
                    callback.onDataLoaded(data)
                }

                override suspend fun onDataNotAvailable() {
                    fetchRMCharacters(callback)
                }
            })

        }
    }

    override suspend fun getRMCharacter(id: Int): LiveData<RMCharacter> {
        return withContext(Dispatchers.IO) {
            localDataSource.getRMCharacter(id)
        }
    }

    override suspend fun getRMLocations(callback: IDataSource.LoadLocationsCallback) {
        return withContext(Dispatchers.IO) {

            if(mCachedLocation.isNotEmpty()) {
                callback.onDataLoaded(mCachedLocation)
                return@withContext
            }

            localDataSource.getRMLocations(object: IDataSource.LoadLocationsCallback {

                override suspend fun onDataLoaded(data: List<Location>) {
                    updateLocationsCache(data)
                    callback.onDataLoaded(data)
                }

                override suspend fun onDataNotAvailable() {
                    fetchRMLocations(callback)
                }
            })
        }
    }

    override suspend fun getRMLocation(id: Int) : LiveData<Location> {
        return withContext(Dispatchers.IO) {
            localDataSource.getRMLocation(id)
        }
    }

    override suspend fun saveRMCharacters(characters: List<RMCharacter>) {
        return withContext(Dispatchers.IO) {
            localDataSource.saveRMCharacters(characters)
        }
    }

    override suspend fun saveRMLocations(location: List<Location>) {
        return withContext(Dispatchers.IO) {

            localDataSource.saveRMLocations(location)

        }
    }

    private suspend fun fetchRMCharacters(loadCharactersCallback: IDataSource.LoadCharactersCallback) {
        return withContext(Dispatchers.IO) {

            remoteDataSource.fetchRMCharacters(object: IDataSource.LoadCharactersCallback{

                override suspend fun onDataLoaded(data: List<RMCharacter>) {
                    updateCharactersCache(data)
                    saveRMCharacters(data)
                    loadCharactersCallback.onDataLoaded(data)
                }

                override suspend fun onDataNotAvailable() {
                    loadCharactersCallback.onDataNotAvailable()
                }
            })
        }
    }

    private suspend fun fetchRMLocations(loadCharactersCallback: IDataSource.LoadLocationsCallback){
        return withContext(Dispatchers.IO) {

            remoteDataSource.fetchRMLocations(object: IDataSource.LoadLocationsCallback{

                override suspend fun onDataLoaded(data: List<Location>) {
                    updateLocationsCache(data)
                    saveRMLocations(data)
                    loadCharactersCallback.onDataLoaded(data)
                }

                override suspend fun onDataNotAvailable() {
                    loadCharactersCallback.onDataNotAvailable()
                }
            })
        }
    }

    private fun updateCharactersCache(data: List<RMCharacter>){
        mCachedCharacters.clear()
        mCachedCharacters.addAll(data)
    }

    private fun updateLocationsCache(data: List<Location>){
        mCachedLocation.clear()
        mCachedLocation.addAll(data)
    }
}