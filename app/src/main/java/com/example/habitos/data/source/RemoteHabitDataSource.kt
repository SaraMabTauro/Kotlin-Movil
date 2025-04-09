package com.example.habitos.data.source

import com.example.habitos.data.model.Habit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteHabitDataSource @Inject constructor() {

    //Aquí va tu cliente Retrofit o similar
    suspend fun getHabits(): List<Habit> {
        TODO("Not yet implemented")
    }

    suspend fun getHabit(id: String): Habit? {
        TODO("Not yet implemented")
    }

    suspend fun createHabit(habit: Habit) {
        TODO("Not yet implemented")
    }

    suspend fun updateHabit(habit: Habit) {
        TODO("Not yet implemented")
    }

    suspend fun deleteHabit(id: String) {
        TODO("Not yet implemented")
    }
}