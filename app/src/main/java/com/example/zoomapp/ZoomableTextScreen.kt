package com.example.zoomapp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.zoomapp.ui.theme.ZoomAppTheme

@Composable
fun ZoomableTextScreen(navController: NavController) {
    var isSettingsVisible by remember { mutableStateOf(false) }

    ZoomAppTheme {
        Column {
            ZoomableText {
                isSettingsVisible = true
                navController.navigate("settings")
            }

            if (isSettingsVisible) {
                SettingsScreen(onBackClick = {
                    navController.popBackStack()
                })
            }
        }
    }
}