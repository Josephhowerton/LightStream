package com.square.lightstream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.lightstream.data.Repository
import com.square.lightstream.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailsViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    var locationLiveData: LiveData<Location>? = null

    private val _shouldShowError = MutableLiveData<Boolean>()
    val shouldShowError: LiveData<Boolean> get() = _shouldShowError

    fun getLocation(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            locationLiveData = repository.getRMLocation(id)
        }
    }
}