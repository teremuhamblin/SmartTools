package com.smarttools.samsung.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Palette inspirée du bleu Samsung One UI
private val SamsungBlue = Color(0xFF0C4DA2)
private val LightBackground = Color(0xFFF7F9FC)
private val DarkBackground = Color(0xFF121212)
private val CardDark = Color(0xFF1E1E1E)

private val LightScheme = lightColorScheme(
    primary = SamsungBlue,
    background = LightBackground,
    surface = Color.White
)

private val DarkScheme = darkColorScheme(
    primary = Color(0xFF6C9EFF),
    background = DarkBackground,
    surface = CardDark
)

@Composable
fun SmartToolsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkScheme else LightScheme,
        content = content
    )
}
