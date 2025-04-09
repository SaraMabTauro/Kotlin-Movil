package com.example.habitos.data.model

import android.location.Location
import java.util.Date

data class Habit(
    val id: String, // Para identificar el hábito en la base de datos
    val title: String,
    val description: String,
    val dueDate: Date?,
    val location: Location?,
    val priority: Int, // 1 (baja) - 5 (alta)
    var isCompleted: Boolean = false
)