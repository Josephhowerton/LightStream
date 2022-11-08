package com.square.lightstream.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.lightstream.R
import com.square.lightstream.data.Repository
import com.square.lightstream.data.source.Result
import com.square.lightstream.util.WhereTo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(): ViewModel() {

    private val _whereToMutableLiveData = MutableLiveData(WhereTo.NoWhere)
    val whereToLiveData: LiveData<WhereTo> get() = _whereToMutableLiveData

    fun onClickCharacterButton() {
        _whereToMutableLiveData.value = WhereTo.CharacterList
    }

    fun onClickLocationButton() {
        _whereToMutableLiveData.value = WhereTo.LocationList
    }

    fun clearNavigation() {
        _whereToMutableLiveData.value = WhereTo.NoWhere
    }
}