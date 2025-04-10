package com.example.habitos.data.source

import com.example.habitos.data.model.Habit
import com.example.habitos.data.api.HabitApiService //Interface Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteHabitDataSource @Inject constructor(private val habitApiService: HabitApiService) { //Inyectamos la dependencia

    suspend fun getHabits(): List<Habit> {
        return habitApiService.getHabits()
    }

    suspend fun getHabit(id: String): Habit? {
        return habitApiService.getHabit(id)
    }

    suspend fun createHabit(habit: Habit) {
        return habitApiService.createHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        return habitApiService.updateHabit(habit.id,habit) // Envio el id para poder actualizar el objeto correcto
    }

    suspend fun deleteHabit(id: String) {
        return habitApiService.deleteHabit(id)
    }
}