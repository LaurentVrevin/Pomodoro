package com.laurentvrevin.pomodorotestapp.presentation.screens

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
import com.laurentvrevin.pomodorotestapp.presentation.components.Picker
import com.laurentvrevin.pomodorotestapp.presentation.components.rememberPickerState
import com.laurentvrevin.pomodorotestapp.presentation.viewmodel.PomodoroViewModel

@Composable
fun BreakTimeScreen(navController: NavController, pomodoroViewModel: PomodoroViewModel) {
    val hoursItem = (0..23).map { it.toString().padStart(2, '0') }
    val minutesItem = (0..59).map { it.toString().padStart(2, '0') }

    val selectedBreakHours = rememberPickerState().apply { selectedItem = "00" }
    val selectedBreakMinutes = rememberPickerState().apply { selectedItem = "00" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Break Time", style = MaterialTheme.typography.headlineSmall)

        Row(horizontalArrangement = Arrangement.Center) {
            Picker(items = hoursItem, state = selectedBreakHours, visibleItemsCount = 5)
            Spacer(modifier = Modifier.width(16.dp))
            Picker(items = minutesItem, state = selectedBreakMinutes, visibleItemsCount = 5)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            val breakHours = selectedBreakHours.selectedItem.toLongOrNull() ?: 0L
            val breakMinutes = selectedBreakMinutes.selectedItem.toLongOrNull() ?: 0L
            val breakTimeInSeconds = breakHours * 3600 + breakMinutes * 60

            pomodoroViewModel.updateBreakTime(breakTimeInSeconds)

            // Si le timer est en cours, on le réinitialise
            if (pomodoroViewModel.isRunning.value) {
                pomodoroViewModel.resetTimer()
            }

            navController.navigate("PomodoroScreen")  // Navigue vers l'écran principal après la validation
        }) {
            Text("Valider")
        }
    }
}
