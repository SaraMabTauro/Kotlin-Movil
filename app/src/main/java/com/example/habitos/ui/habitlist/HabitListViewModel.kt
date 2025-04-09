package com.example.habitos.ui.habitlist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitos.data.model.Habit
import com.example.habitos.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitListViewModel @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {

    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits

    init {
        loadHabits()
    }

    fun loadHabits() {
        viewModelScope.launch {
            habitRepository.getHabits().collect {
                _habits.value = it
            }
        }
    }

    fun deleteHabit(habitId: String) {
        viewModelScope.launch {
            habitRepository.deleteHabit(habitId)
            loadHabits() // Recargar la lista
        }
    }
}