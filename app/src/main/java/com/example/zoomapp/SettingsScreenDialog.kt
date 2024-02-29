package com.example.zoomapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreenDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current

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
