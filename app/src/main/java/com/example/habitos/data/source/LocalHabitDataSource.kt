package com.example.habitos.data.source

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.habitos.data.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalHabitDataSource @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val gson = Gson()

    fun getHabits(): Flow<List<Habit>> = flow {
        val habitsJson = sharedPreferences.getString("habits", null)
        val type = object : TypeToken<List<Habit>>() {}.type
        val habits: List<Habit> = gson.fromJson(habitsJson, type) ?: emptyList()
        emit(habits)
    }

    suspend fun getHabit(id: String): Habit? {
        val habitsJson = sharedPreferences.getString("habits", null)
        val type = object : TypeToken<List<Habit>>() {}.type
        val habits: List<Habit> = gson.fromJson(habitsJson, type) ?: emptyList()
        return habits.find { it.id == id }
    }

    suspend fun createHabit(habit: Habit) {
        val habitsJson = sharedPreferences.getString("habits", null)
        val type = object : TypeToken<List<Habit>>() {}.type
        val habits: MutableList<Habit> = (gson.fromJson(habitsJson, type) ?: emptyList<Habit>()).toMutableList()
        habits.add(habit)
        val newHabitsJson = gson.toJson(habits)
        sharedPreferences.edit().putString("habits", newHabitsJson).apply()
    }

    suspend fun updateHabit(habit: Habit) {
        val habitsJson = sharedPreferences.getString("habits", null)
        val type = object : TypeToken<List<Habit>>() {}.type
        val habits: MutableList<Habit> = (gson.fromJson(habitsJson, type) ?: emptyList<Habit>()).toMutableList()
        val index = habits.indexOfFirst { it.id == habit.id }
        if (index != -1) {
            habits[index] = habit
            val newHabitsJson = gson.toJson(habits)
            sharedPreferences.edit().putString("habits", newHabitsJson).apply()
        }
    }

    suspend fun deleteHabit(id: String) {
        val habitsJson = sharedPreferences.getString("habits", null)
        val type = object : TypeToken<List<Habit>>() {}.type
        val habits: MutableList<Habit> = (gson.fromJson(habitsJson, type) ?: emptyList<Habit>()).toMutableList()
        habits.removeAll { it.id == id }
        val newHabitsJson = gson.toJson(habits)
        sharedPreferences.edit().putString("habits", newHabitsJson).apply()
    }
}