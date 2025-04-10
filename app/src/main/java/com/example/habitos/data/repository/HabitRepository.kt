package com.example.habitos.data.repository;

import com.example.habitos.data.model.Habit;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

interface HabitRepository {
    fun getHabits(): Flow<kotlin.collections.List<Habit>>
    suspend fun getHabit(id: String): Habit?
    suspend fun createHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(id: String)
}