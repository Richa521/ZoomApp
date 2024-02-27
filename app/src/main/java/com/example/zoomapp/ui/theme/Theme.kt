package com.example.zoomapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.zoomapp.ThemeState

private val DarkColorPalette = darkColors(
    background = BackgroundColorDark,
    primary = PrimaryDark,
    secondary = SecondaryLight
)

private val LightColorPalette = lightColors(
    background = BackgroundColorLight,
    primary = PrimaryLight,
    secondary = SecondaryLight,
    surface = SurfaceLight
)

@Composable
fun ZoomAppTheme(content: @Composable () -> Unit) {
    val isSystemDarkMode = isSystemInDarkTheme()

    if (!ThemeState.isInitialized) {
        ThemeState.initialize(isSystemDarkMode)
    }

    val colors = if (ThemeState.isDarkMode) {
        DarkColorPalette
    } else {
        LightColorPalette
    }



    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}