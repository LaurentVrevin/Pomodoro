package com.laurentvrevin.pomodoro.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel
import com.laurentvrevin.pomodoro.utils.formatTime

@Composable
fun PomodoroScreen(navController: NavController, pomodoroViewModel: PomodoroViewModel){

    val progress by pomodoroViewModel.progress.collectAsState()
    val worktime by pomodoroViewModel.worktime.collectAsState()
    val isRunning by pomodoroViewModel.isRunning.collectAsState()
    var labelPushPlayPause = if (isRunning) "Pause" else "Play"

    Box(
        modifier = Modifier
        .fillMaxSize()
    ) {
        IconButton(
            onClick = { navController.navigate("Settings") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            ) {
                // Barre de progression circulaire autour du bouton
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(270.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 12.dp
                )

                Spacer(modifier = Modifier.height(32.dp))


                Button(
                    onClick = {
                        pomodoroViewModel.toggleTimer(worktime)
                    },
                    modifier = Modifier
                        .size(250.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = formatTime(worktime),
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )

                        // Texte "Play" ou "Pause" en bas du bouton
                        Text(
                            text = labelPushPlayPause,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(0.dp, 0.dp, 0.dp, 16.dp)
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                pomodoroViewModel.resetTimer()
            },
                modifier = Modifier
                    .padding(16.dp)
                    ,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = "Reset",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 8.dp,0.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Reset",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}
