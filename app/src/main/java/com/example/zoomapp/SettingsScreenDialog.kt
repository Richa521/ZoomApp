package com.example.zoomapp

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.edit


@Composable
fun SettingsScreenDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current

    var isDarkMode by rememberSaveable { mutableStateOf(ThemeState.isDarkMode) }
    var isSystemMode by rememberSaveable {
        mutableStateOf(
            if (!ThemeState.isInitialized) {
                val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
                sharedPreferences.getBoolean("SystemMode", !isDarkMode)
            } else {
                ThemeState.isSystemMode
            }
        )
    }

    DisposableEffect(context) {
        if (!ThemeState.isInitialized) {
            val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
            isDarkMode = sharedPreferences.getBoolean("DarkMode", false)
            isSystemMode = sharedPreferences.getBoolean("SystemMode", !isDarkMode)
            ThemeState.isInitialized = true
        }

        onDispose {
            val sharedPreferences = context.getSharedPreferences("SharedPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit()
                .putBoolean("DarkMode", isDarkMode)
                .putBoolean("SystemMode", isSystemMode)
                .apply()

            ThemeState.setIsDarkMode(context, isDarkMode)
            ThemeState.setIsSystemMode(context, isSystemMode)
        }
    }


    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Settings",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        },
        text = {
            Column {
                RadioButtonOption(
                    text = "Dark mode",
                    selected = isDarkMode,
                    onClick = {
                        isDarkMode = true
                        isSystemMode = false
                    }
                )
                RadioButtonOption(
                    text = "Light mode",
                    selected = !isDarkMode && !isSystemMode,
                    onClick = {
                        isDarkMode = false
                        isSystemMode = false
                    }
                )
                RadioButtonOption(
                    text = "System default",
                    selected = isSystemMode,
                    onClick = {
                        isDarkMode = false
                        isSystemMode = true
                    }
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(16.dp),
        confirmButton = {
            Button(
                onClick = { onDismiss() },
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = "OK")
            }
        }
    )
}

@Composable
fun RadioButtonOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colors.primary
        )
    }
}
