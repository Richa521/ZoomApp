package com.example.zoomapp

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeState {
    private var _isDarkMode by mutableStateOf(false)
    private var _isSystemMode by mutableStateOf(false)

    val isDarkMode: Boolean
        get() = _isDarkMode
    val isSystemMode: Boolean
        get() = _isSystemMode

    var isInitialized by mutableStateOf(false)

    fun initialize(context: Context) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
        _isDarkMode = sharedPreferences.getBoolean("DarkMode", false)
        _isSystemMode = sharedPreferences.getBoolean("SystemMode", !isDarkMode)
        isInitialized = true
        applyTheme()
    }

    fun setIsDarkMode(context: Context, isDarkMode: Boolean) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("DarkMode", isDarkMode).apply()
        _isDarkMode = isDarkMode
        _isSystemMode = false
        applyTheme()
    }

    fun setIsSystemMode(context: Context, isSystemMode: Boolean) {
        val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("SystemMode", isSystemMode).apply()
        _isSystemMode = isSystemMode
        applyTheme()
    }

    private fun applyTheme() {
        val newMode = if (_isSystemMode) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        } else {
            if (_isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(newMode)
    }


}