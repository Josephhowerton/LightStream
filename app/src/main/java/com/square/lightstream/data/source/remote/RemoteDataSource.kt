package com.square.lightstream.data.source.remote

import android.util.Log
import com.square.lightstream.data.IDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: RMService) : IRemoteDataSource {

    override suspend fun fetchRMCharacters(loadCharactersCallback: IDataSource.LoadCharactersCallback){
        try {

            val response = service.fetchRMCharacters()

            response.body()?.let {

                loadCharactersCallback.onDataLoaded(it.results)

            } ?: throw Exception("Response Body: Null")

        } catch (e: Exception)
        {
            Log.println(Log.ASSERT, "RemoteDataSource", e.toString())
            loadCharactersCallback.onDataNotAvailable()
        }
    }

    override suspend fun fetchRMLocations(loadCharactersCallback: IDataSource.LoadLocationsCallback){
        try {

            val response = service.fetchRMLocations()

            response.body()?.let {

                loadCharactersCallback.onDataLoaded(it.results)

            } ?: throw Exception("Response Body: Null")

        } catch (e: Exception)
        {
            loadCharactersCallback.onDataNotAvailable()
        }
    }
}