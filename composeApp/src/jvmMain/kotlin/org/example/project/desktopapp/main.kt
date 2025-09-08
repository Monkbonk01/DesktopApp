package org.example.project.desktopapp

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

// ./gradlew run

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(300.dp, 400.dp)
    )
    Window(
        title = "Spieleprogramm",
        onCloseRequest = ::exitApplication,
        state = state,
    ) {
        start()
    }
}