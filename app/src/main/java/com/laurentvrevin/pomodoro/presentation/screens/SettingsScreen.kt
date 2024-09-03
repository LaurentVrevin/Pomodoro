package com.laurentvrevin.pomodoro.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel
import com.laurentvrevin.pomodoro.utils.PreviewPomodoroViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController, pomodoroViewModel: PomodoroViewModel){


    // Affichage de la page des paramètres

    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) { innerPadding ->

        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigateUp() }
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
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
