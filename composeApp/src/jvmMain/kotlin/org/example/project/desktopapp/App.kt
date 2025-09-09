package org.example.project.desktopapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview



import desktopapp.composeapp.generated.resources.Res
import desktopapp.composeapp.generated.resources.compose_multiplatform
import javax.sql.RowSetEvent
import kotlin.system.exitProcess




@Composable
@Preview
fun schereSteinPapier() {

    var users by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf(value = "") }
    var userSelectionIssue by remember { mutableStateOf("") }
    var computerSelection by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var userWin by remember { mutableStateOf(0) }
    var computerWin by remember { mutableStateOf(0) }
    var draw by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    var visible2 by remember { mutableStateOf(true) }


    val black = Color(0, 0, 0)

    //Funktion für die Auswahl des Computers per Zufall
    fun computerRandom() {
        val computerRandomChoice = (1..3).random()
        computerSelection = when (computerRandomChoice) {
            1 -> "Schere"
            2 -> "Stein"
            3 -> "Papier"
            else -> "Unbekannt"
        }
    }

    //Funktion zuzm prüfen wer gewonnen hat und zum zählen des Ergebnisses
    fun resultReview() {
        when (userSelectionIssue) {
            "Schere" if computerSelection == "Schere" -> {
                result = "Unentschieden"
            }
            "Schere" if computerSelection == "Stein" -> {
                result = "Computer gewinnt"
            }
            "Schere" if computerSelection == "Papier" -> {
                result = "Spieler gewinnt"
            }
            "Stein" if computerSelection == "Stein" -> {
                result = "Unentschieden"
            }
            "Stein" if computerSelection == "Papier" -> {
                result = "Computer gewinnt"
            }
            "Stein" if computerSelection == "Schere" -> {
                result = "Spieler gewinnt"
            }
            "Papier" if computerSelection == "Papier" -> {
                result = "Unentschieden"
            }
            "Papier" if computerSelection == "Schere" -> {
                result = "Computer gewinnt"
            }
            "Papier" if computerSelection == "Stein" -> {
                result = "Spieler gewinnt"
            }
        }

        when (result) {
            "Spieler gewinnt" -> {
                userWin++
            }

            "Computer gewinnt" -> {
                computerWin++
            }

            "Unentschieden" -> {
                draw++
            }
        }
    }

    //Funktion für die Tabelle
    @Composable
    fun TableCard(content: String, modifier: Modifier = Modifier, header: Boolean = false, color: Color) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(if (header) Color(0, 0, 0) else Color.White)
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .height(60.dp)
                .width(60.dp)

        ) {
            Text(
                text = content,
                fontSize = if (header) 18.sp else 16.sp,
                fontWeight = if (header) FontWeight.Bold else FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center),
                color = color
            )
        }
    }

    fun selectionButton() {
        computerRandom()
        resultReview()
    }

    @Composable
    fun beginn() {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .safeContentPadding()
                .fillMaxSize()
        ) {
            Column(
            ) {
                Text(
                    "Herzlichen Willkommen bei \nSchere, Stein, Papier",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text("Bitte gib deinen Namen ein:")
                TextField(
                    value = users,
                    onValueChange = { users = it },
                    label = { Text("Name") },
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = {
                    userName = "Heutiger Spieler ist $users"
                    visible = true
                    visible2 = false
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (black)
                    )) {
                    Text("Übertragen")
                }
            }
        }
    }

    @Composable
    fun spiel() {
        Column(
            modifier = Modifier
                .padding(20.dp)

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(userName)
            Spacer(modifier = Modifier.height(10.dp))
            Text("${users}, bitte treffe deine Wahl:")

            Row() {
                Button(onClick = {
                    userSelectionIssue = "Schere"
                    selectionButton()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (black)
                    )) {
                    Text("Schere")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {
                    userSelectionIssue = "Stein"
                    selectionButton()
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (black)
                    )) {
                    Text("Stein")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {
                        userSelectionIssue = "Papier"
                        selectionButton()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (black)
                    )) {
                    Text("Papier")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text("Der Spieler wählte: $userSelectionIssue")
            Spacer(modifier = Modifier.height(20.dp))
            Text("Der Computer wählte: $computerSelection")
            Spacer(modifier = Modifier.height(20.dp))

            Text(result, fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TableCard("Wins", Modifier.weight(1f), color = Color.White, header = true)
                    TableCard("Loss", Modifier.weight(1f), color = Color.White, header = true)
                    TableCard("Draws", Modifier.weight(1f), color = Color.White, header = true)
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    TableCard(userWin.toString(), Modifier.weight(1f), color = Color.Green)
                    TableCard(computerWin.toString(), Modifier.weight(1f), color = Color.Red)
                    TableCard(draw.toString(), Modifier.weight(1f), color = Color.Blue)
                }
            }

        }
    }

            MaterialTheme {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .safeContentPadding()
                        .fillMaxSize()
                )
                {
                    if(visible2) {
                beginn() }
                }

                if (visible) {
                spiel()

                }
            }
}



