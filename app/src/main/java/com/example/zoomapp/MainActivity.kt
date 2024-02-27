package com.example.zoomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zoomapp.ui.theme.ZoomAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val isSystemDarkMode = isSystemInDarkTheme()

            ThemeState.initialize(isSystemDarkMode)

            ZoomAppTheme{
                NavHost(navController = navController, startDestination = "zoomableText") {
                    composable("zoomableText") {
                        ZoomableTextScreen(navController = navController)
                    }
                    composable("settings") {
                        SettingsScreen(onBackClick = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}
