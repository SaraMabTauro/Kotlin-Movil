package com.example.habitos.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorMessage(message: String) {
    Text(text = message, color = Color.Red)
}

@Preview
@Composable
fun ErrorMessagePreview() {
    ErrorMessage(message = "Este es un mensaje de error")
}