package com.laurentvrevin.pomodoro.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    val progress by pomodoroViewModel.progress.collectAsState()
    val worktime by pomodoroViewModel.worktime.collectAsState()


    // Affichage de la page des paramètres
    Scaffold(
        modifier = Modifier
            .fillMaxSize()

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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section pour le temps de travail
            
            Text(
                text = "Work Time",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(16.dp))


            // Section pour le temps de pause
            Text(
                text = "Break Time",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {})
            {
                Text(text = "Save")

        }
            val hoursItem = (0..23).map { it.toString() }
            val minutesItem = (0..59).map { it.toString() }
            // État pour l'élément sélectionné
            val selectedHours = rememberPickerState()
            val selectedMinutes = rememberPickerState()


            // Interface utilisateur principale

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Sélectionne un nombre", style = MaterialTheme.typography.labelSmall)

                    Row(){

                        // Utilisation du Picker avec une plage de nombres
                        Picker(
                            items = hoursItem,
                            selectedItem = selectedHours,
                            visibleItemsCount = 4,
                            label = "Heures",

                        )
                        Picker(
                            items = minutesItem,
                            selectedItem = selectedMinutes,
                            visibleItemsCount = 4,
                            label = "Minutes"
                        )
                    }

                    // Afficher l'élément sélectionné
                    Text(text = "Tu as sélectionné: ${selectedHours.value} + ${selectedMinutes.value}", style = MaterialTheme.typography.labelSmall)
                }
            }
    }

}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    // Création d'un NavController factice
    val navController = NavController(LocalContext.current)


    // Affichage de l'écran des paramètres dans la preview
    SettingsScreen(navController = navController, pomodoroViewModel = PreviewPomodoroViewModel())
}
