package com.example.zoomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

            var splashScreenFinished by remember { mutableStateOf(false) }

            SplashScreen {
                splashScreenFinished = true
            }

            if (splashScreenFinished) {
                ZoomAppTheme {
                    NavHost(navController = navController, startDestination = "zoomableText") {
                        composable("zoomableText") {
                            ZoomableTextScreen()
                        }
                        composable("settings") {
                            SettingsScreenDialog {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
