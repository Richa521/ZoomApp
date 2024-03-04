package com.example.zoomapp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.zoomapp.ui.theme.ZoomAppTheme

@Composable
fun ZoomableTextScreen() {
    var isSettingsVisible by remember { mutableStateOf(false) }

    ZoomAppTheme {
        Column {
            ZoomableText {
                isSettingsVisible = true
            }

            if (isSettingsVisible) {
                SettingsScreenDialog {
                    isSettingsVisible = false
                }
            }
        }
    }
}