package com.example.zoomapp

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeState {

    private var _isDarkMode by mutableStateOf(false)

    val isDarkMode: Boolean
        get() = _isDarkMode

    var isInitialized by mutableStateOf(false)

    fun initialize(isDarkMode: Boolean) {
        _isDarkMode = isDarkMode
        isInitialized = true
    }

    fun setIsDarkMode(context: Context, isDarkMode: Boolean) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("DarkMode", isDarkMode).apply()
        _isDarkMode = isDarkMode
    }
}
