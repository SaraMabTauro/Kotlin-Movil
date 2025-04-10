package com.example.habitos.ui.habitdetail

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor() : ViewModel() {

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    private val _habitDetailState = MutableLiveData<HabitDetailState>()
    val habitDetailState: LiveData<HabitDetailState> = _habitDetailState

    fun setLocation(location: Location) {
        _location.value = location
    }

    sealed class HabitDetailState {
        object Idle : HabitDetailState()
        object Loading : HabitDetailState()
        data class Success(val message: String) : HabitDetailState()
        data class Error(val message: String) : HabitDetailState()
    }
}