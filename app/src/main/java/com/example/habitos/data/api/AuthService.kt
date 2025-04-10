package com.example.habitos.data.api

import com.example.habitos.data.model.LoginRequest
import com.example.habitos.data.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class AuthService {
    private val client = OkHttpClient()
    private val baseUrl = "http://192.168.0.16:3000" // Ajusta a tu URL base

    suspend fun login(username: String, password: String): LoginResponse? {
        val loginRequest = LoginRequest(username, password)

        // Crear el objeto JSON manualmente para asegurar la estructura
        val jsonBody = """
            {
                "username": "${loginRequest.username}",
                "password": "${loginRequest.password}"
            }
        """.trimIndent()

        val request = Request.Builder()
            .url("$baseUrl/users/token") // Endpoint correcto
            .post(jsonBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        return try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                // Leer el cuerpo de la respuesta de forma segura
                val responseBody = response.body?.string() ?: return null
                // Parsear el JSON manualmente para controlar los errores
                val jsonResponse = JSONObject(responseBody)
                LoginResponse(
                    accessToken = jsonResponse.optString("access", ""),
                    refreshToken = jsonResponse.optString("refresh", "")
                )
            } else {
                println("Login failed: ${response.code} - ${response.message}")
                println("Response body: ${response.body?.string()}") // Imprime el cuerpo de la respuesta para depurar
                null
            }
        } catch (e: IOException) {
            println("Error during login: ${e.message}")
            e.printStackTrace()
            null
        } catch (e: Exception) {
            println("Unexpected error during login: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}