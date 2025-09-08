package org.example.project.desktopapp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlin.system.exitProcess

@Composable
@Preview
fun start() {

    var showDialog by remember { mutableStateOf(false) }
    val black = Color(0, 0, 0)

    Column(
        modifier = Modifier
            .padding(20.dp)
            .safeContentPadding()
            .fillMaxSize()
    ) {
        Text("Herzlichen Willkommen \nbei meinem kleinen Spieleprogramm",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button({ showDialog = true},
            colors  = ButtonDefaults.buttonColors(
                containerColor = (black),
                //contentColor = Color.White
            )) {
            Text("Schere, Stein, Papier")
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = (black)
            )) {
            Text("Beenden")
        }

    }

    if (showDialog) {
        val state = rememberWindowState(
            size = DpSize(400.dp, 500.dp)
        )
        Window(
            title = "Spieleprogramm",
            onCloseRequest = {showDialog = false},
            state = state,
        ) {
            schereSteinPapier()
        }
    }


}



