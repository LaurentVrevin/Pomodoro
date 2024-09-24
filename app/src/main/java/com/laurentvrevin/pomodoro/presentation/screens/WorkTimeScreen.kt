package com.laurentvrevin.pomodoro.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.laurentvrevin.pomodoro.presentation.components.Picker
import com.laurentvrevin.pomodoro.presentation.components.rememberPickerState
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel

@Composable
fun WorkTimeScreen(navController: NavController, pomodoroViewModel: PomodoroViewModel) {
    val hoursItem = (0..23).map { it.toString().padStart(2, '0') }
    val minutesItem = (0..90).map { it.toString().padStart(2, '0') }

    val selectedWorkHours = rememberPickerState().apply { selectedItem = "00" }
    val selectedWorkMinutes = rememberPickerState().apply { selectedItem = "00" }


        // Contenu principal au centre
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),  // Pour laisser de la place pour le bouton en bas
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Work Time", style = MaterialTheme.typography.headlineSmall)

            Row(horizontalArrangement = Arrangement.Center) {
                Picker(items = hoursItem, state = selectedWorkHours, visibleItemsCount = 5)
                Spacer(modifier = Modifier.width(16.dp))
                Picker(items = minutesItem, state = selectedWorkMinutes, visibleItemsCount = 5)
            }
            Spacer(modifier = Modifier.height(32.dp))
            // Bouton placé en bas avec un padding de 16dp
            Button(
                onClick = {
                    val workHours = selectedWorkHours.selectedItem.toLongOrNull() ?: 0L
                    val workMinutes = selectedWorkMinutes.selectedItem.toLongOrNull() ?: 0L
                    val workTimeInSeconds = workHours * 3600 + workMinutes * 60

                    pomodoroViewModel.updateWorkTime(workTimeInSeconds)

                    navController.navigate("BreakTimeScreen")  // Navigue vers l'écran de la pause
                },

                ) {
                Text("Suivant")
            }
        }


    }
