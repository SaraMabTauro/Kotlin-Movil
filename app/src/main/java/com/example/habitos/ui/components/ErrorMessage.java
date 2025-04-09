package com.example.habitos.ui.components;

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorMessage(message: String) {
    // Implementa la UI para mostrar el mensaje de error
    // Por ejemplo:
    // Text(text = message, color = Color.Red)
}

@Preview
@Composable
fun ErrorMessagePreview() {
    ErrorMessage(message = "Este es un mensaje de error")
}