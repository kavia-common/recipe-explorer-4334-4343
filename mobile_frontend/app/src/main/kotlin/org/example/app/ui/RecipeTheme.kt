package org.example.app.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PrimaryColor = Color(0xFFFF7043)
private val SecondaryColor = Color(0xFFFFA726)
private val AccentColor = Color(0xFF66BB6A)

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    onPrimary = Color.White,
    primaryContainer = PrimaryColor.copy(alpha=0.12f),
    secondaryContainer = SecondaryColor.copy(alpha=0.12f),
    background = Color(0xFFF8F8F8),
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD32F2F),
    onError = Color.White
)

@Composable
fun RecipeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
