package com.square.lightstream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.lightstream.data.IDataSource
import com.square.lightstream.data.Repository
import com.square.lightstream.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val repository: Repository): ViewModel(), IDataSource.LoadLocationsCallback {

    private val _locationMutableLiveData = MutableLiveData<List<Location>>()
    val locationLiveData: LiveData<List<Location>> get() = _locationMutableLiveData

    private val _shouldShowError = MutableLiveData<Boolean>()
    val shouldShowError: LiveData<Boolean> get() = _shouldShowError

    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO){
            repository.getRMLocations(this@LocationsViewModel)
        }
    }

    override suspend fun onDataLoaded(data: List<Location>) {
        _locationMutableLiveData.postValue(data.sortedBy { it.name })
    }

    override suspend fun onDataNotAvailable() {
        _shouldShowError.value = true
    }

    fun clearError() {
        _shouldShowError.value = false
    }

}