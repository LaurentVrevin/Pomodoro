package com.laurentvrevin.pomodoro.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.laurentvrevin.pomodoro.presentation.components.Picker
import com.laurentvrevin.pomodoro.presentation.components.rememberPickerState
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController, pomodoroViewModel: PomodoroViewModel) {

    val worktime by pomodoroViewModel.worktime.collectAsState()
    val breaktime by pomodoroViewModel.breaktime.collectAsState()

    val hoursItem = (0..23).map { it.toString().padStart(2, '0') }
    val minutesItem = (0..59).map { it.toString().padStart(2, '0') }

    // Initialisation avec des valeurs par défaut (par exemple, 0 heures et 0 minutes)
    val selectedWorkHours = rememberPickerState().apply { selectedItem = "00" }
    val selectedWorkMinutes = rememberPickerState().apply { selectedItem = "00" }
    val selectedBreakHours = rememberPickerState().apply { selectedItem = "00" }
    val selectedBreakMinutes = rememberPickerState().apply { selectedItem = "00" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Work Time", style = MaterialTheme.typography.headlineSmall)
        Row(horizontalArrangement = Arrangement.Center) {
            Picker(items = hoursItem, state = selectedWorkHours, visibleItemsCount = 5)
            Spacer(modifier = Modifier.width(16.dp))
            Picker(items = minutesItem, state = selectedWorkMinutes, visibleItemsCount = 5)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Break Time", style = MaterialTheme.typography.headlineSmall)
        Row(horizontalArrangement = Arrangement.Center) {
            Picker(items = hoursItem, state = selectedBreakHours, visibleItemsCount = 5)
            Spacer(modifier = Modifier.width(16.dp))
            Picker(items = minutesItem, state = selectedBreakMinutes, visibleItemsCount = 5)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            // Ajout de validation avant de convertir en Long
            val workHours = selectedWorkHours.selectedItem.takeIf { it.isNotEmpty() }?.toLong() ?: 0L
            val workMinutes = selectedWorkMinutes.selectedItem.takeIf { it.isNotEmpty() }?.toLong() ?: 0L
            val breakHours = selectedBreakHours.selectedItem.takeIf { it.isNotEmpty() }?.toLong() ?: 0L
            val breakMinutes = selectedBreakMinutes.selectedItem.takeIf { it.isNotEmpty() }?.toLong() ?: 0L

            val workTimeInSeconds = workHours * 3600 + workMinutes * 60
            val breakTimeInSeconds = breakHours * 3600 + breakMinutes * 60

            // Met à jour les durées dans le ViewModel
            pomodoroViewModel.updateWorkTime(workTimeInSeconds)
            pomodoroViewModel.updateBreakTime(breakTimeInSeconds)

            // Arrête le timer si en cours d'exécution
            if (pomodoroViewModel.isRunning.value) {
                pomodoroViewModel.resetTimer()  // On arrête et réinitialise le timer
            }

            // Navigue vers l'écran principal
            navController.navigateUp()
        }) {
            Text("Save Settings")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val navController = NavController(LocalContext.current)

    // Affichage de l'écran des paramètres dans la preview
    //SettingsScreen(navController = navController, pomodoroViewModel = PreviewPomodoroViewModel())
}
