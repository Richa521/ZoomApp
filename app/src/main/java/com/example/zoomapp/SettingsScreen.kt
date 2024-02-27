package com.example.zoomapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Settings",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            },
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "back"
                    )
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.DarkMode,
                contentDescription = "DarkMode",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(30.dp)
                    .align(alignment = CenterVertically),
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = CenterVertically),
                text = "Dark mode",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.width(20.dp))

            Switch(
                checked = ThemeState.isDarkMode,
                onCheckedChange = {
                    ThemeState.setIsDarkMode(context = context, isDarkMode = it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.secondary
                )
            )
        }
    }
}