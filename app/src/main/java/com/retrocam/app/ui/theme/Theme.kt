package com.retrocam.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val RetroDarkBackground = Color(0xFF121212)
val RetroSurface = Color(0xFF1C1C1C)
val RetroSurfaceBorder = Color(0xFF2E2E2A)
val RetroAmber = Color(0xFFE8A838)
val RetroTextPrimary = Color(0xFFF2F2EE)
val RetroTextSecondary = Color(0xFF9E9E98)
val RetroError = Color(0xFFD96B52)

private val RetroDarkColorScheme = darkColorScheme(
    primary = RetroAmber,
    onPrimary = RetroDarkBackground,
    secondary = RetroAmber,
    onSecondary = RetroDarkBackground,
    background = RetroDarkBackground,
    onBackground = RetroTextPrimary,
    surface = RetroSurface,
    onSurface = RetroTextPrimary,
    surfaceVariant = RetroSurface,
    onSurfaceVariant = RetroTextSecondary,
    outline = RetroSurfaceBorder,
    error = RetroError,
)

@Composable
fun RetroCamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RetroDarkColorScheme,
        content = content,
    )
}
