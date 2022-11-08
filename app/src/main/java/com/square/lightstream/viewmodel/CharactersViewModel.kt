package com.square.lightstream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.data.IDataSource
import com.square.lightstream.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: Repository) : ViewModel(), IDataSource.LoadCharactersCallback {

    private val _charactersMutableLiveData = MutableLiveData<List<RMCharacter>>()
    val charactersLiveData: LiveData<List<RMCharacter>> get() = _charactersMutableLiveData

    private val _shouldShowError = MutableLiveData<Boolean>()
    val shouldShowError: LiveData<Boolean> get() = _shouldShowError

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO){
            repository.getRMCharacters(this@CharactersViewModel)
        }
    }

    override suspend fun onDataLoaded(data: List<RMCharacter>) {
        _charactersMutableLiveData.postValue(data.sortedBy { it.name })
    }

    override suspend fun onDataNotAvailable() {
        _shouldShowError.value = true
    }

    fun clearError() {
        _shouldShowError.value = false
    }
}