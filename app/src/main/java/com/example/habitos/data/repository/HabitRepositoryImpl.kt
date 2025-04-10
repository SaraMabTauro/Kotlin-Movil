package com.example.habitos.data.repository

import com.example.habitos.data.model.Habit
import com.example.habitos.data.source.LocalHabitDataSource
import com.example.habitos.data.source.RemoteHabitDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val localDataSource: LocalHabitDataSource,
    private val remoteDataSource: RemoteHabitDataSource
) : HabitRepository {
    override fun getHabits(): Flow<List<Habit>> {
        return localDataSource.getHabits()
    }

    override suspend fun getHabit(id: String): Habit? {
        return localDataSource.getHabit(id)
    }

    override suspend fun createHabit(habit: Habit) {
        localDataSource.createHabit(habit)
        remoteDataSource.createHabit(habit) // Sincronizar con el backend
    }

    override suspend fun updateHabit(habit: Habit) {
        localDataSource.updateHabit(habit)
        remoteDataSource.updateHabit(habit)
    }

    override suspend fun deleteHabit(id: String) {
        localDataSource.deleteHabit(id)
        remoteDataSource.deleteHabit(id)
    }
}