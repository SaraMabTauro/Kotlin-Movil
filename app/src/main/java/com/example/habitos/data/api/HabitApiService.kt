package com.example.habitos.data.api

import com.example.habitos.data.model.Habit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HabitApiService {
    @GET("/habits")
    suspend fun getHabits(): List<Habit>

    @GET("/habits/{id}")
    suspend fun getHabit(@Path("id") id: String): Habit

    @POST("/habits")
    suspend fun createHabit(@Body habit: Habit)

    @PUT("/habits/{id}")
    suspend fun updateHabit(@Path("id") id: String, @Body habit: Habit)

    @DELETE("/habits/{id}")
    suspend fun deleteHabit(@Path("id") id: String)
}