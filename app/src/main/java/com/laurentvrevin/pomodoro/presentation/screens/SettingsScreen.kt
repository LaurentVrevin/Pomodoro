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
import com.laurentvrevin.pomodoro.utils.PreviewPomodoroViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController, pomodoroViewModel: PomodoroViewModel){

    val worktime by pomodoroViewModel.worktime.collectAsState()

    // Affichage de la page des paramètres
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        IconButton(
            modifier = Modifier.padding(24.dp),
            onClick = { navController.navigateUp() }
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section pour le temps de travail
            Text(
                text = "Work Time",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))


            val hoursItem = (0..23).map { it.toString().padStart(2, '0') }
            val minutesItem = (0..59).map { it.toString().padStart(2, '0') }

            // État pour l'élément sélectionné
            val selectedHours = rememberPickerState()
            val selectedMinutes = rememberPickerState()

            // Interface utilisateur principale
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Sélectionne un nombre", style = MaterialTheme.typography.labelSmall)

                Row(horizontalArrangement = Arrangement.Center) {
                    // Utilisation du Picker pour les heures
                    Picker(
                        items = hoursItem,
                        state = selectedHours, // Utilisation de l'état
                        visibleItemsCount = 5, // Nombre impair pour centrer
                        textStyle = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Utilisation du Picker pour les minutes
                    Picker(
                        items = minutesItem,
                        state = selectedMinutes, // Utilisation de l'état
                        visibleItemsCount = 5, // Nombre impair pour centrer
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Afficher l'élément sélectionné
                Text(
                    text = "Tu as sélectionné: ${selectedHours.selectedItem} heures + ${selectedMinutes.selectedItem} minutes",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val navController = NavController(LocalContext.current)

    // Affichage de l'écran des paramètres dans la preview
    SettingsScreen(navController = navController, pomodoroViewModel = PreviewPomodoroViewModel())
}
