package com.example.zoomapp

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ZoomableText(onSettingsClick: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var textSize by remember { mutableStateOf(25.sp) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offset = if (scale > 1) {
                        Offset(offset.x + pan.x * scale, offset.y + pan.y * scale)
                    } else {
                        Offset(0f, 0f)
                    }
                    scale = scale.coerceIn(1f, 15f)
                    textSize = (15 * scale).sp
                }
            }
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "ZoomApp",
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            actions = {
                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = AppBarHeight)
        ) {
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = textSize,
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append("Hello World, Welcome to my zoom app.")
                }
            }

            BasicText(
                text = annotatedString,
                modifier = Modifier
                    .padding(16.dp)
                    .background(MaterialTheme.colors.background)
            )
        }
    }
}

val AppBarHeight = 56.dp


