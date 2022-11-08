package com.square.lightstream.data.source.remote

import com.square.lightstream.data.IDataSource

interface IRemoteDataSource {
    suspend fun fetchRMCharacters(loadCharactersCallback: IDataSource.LoadCharactersCallback)
    suspend fun fetchRMLocations(loadCharactersCallback: IDataSource.LoadLocationsCallback)

}