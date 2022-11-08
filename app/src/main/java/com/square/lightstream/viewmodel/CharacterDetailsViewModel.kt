package com.square.lightstream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var characterLiveData: LiveData<RMCharacter>? = null

    private val _shouldShowError = MutableLiveData<Boolean>()
    val shouldShowError: LiveData<Boolean> get() = _shouldShowError

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            characterLiveData = repository.getRMCharacter(id)
        }
    }
}