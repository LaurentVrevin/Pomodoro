package com.laurentvrevin.pomodorotestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.laurentvrevin.pomodorotestapp.data.preferences.PreferencesManager
import com.laurentvrevin.pomodorotestapp.data.repository.TimerRepositoryImpl
import com.laurentvrevin.pomodorotestapp.navigation.NavGraph
import com.laurentvrevin.pomodorotestapp.presentation.theme.PomodoroTheme
import com.laurentvrevin.pomodorotestapp.presentation.viewmodel.PomodoroViewModel
import com.laurentvrevin.pomodorotestapp.presentation.viewmodel.PomodoroViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val preferencesManager = PreferencesManager(applicationContext)

        // Instance le repository qu'on passe à la Factory
        val repository by lazy { TimerRepositoryImpl(preferencesManager = preferencesManager) }

        // Instance le ViewModel en utilisant la Factory
        val pomodoroViewModel: PomodoroViewModel by viewModels {
            PomodoroViewModelFactory(application, repository)
        }
            
        setContent {
            PomodoroTheme {
                PomodoroApp(pomodoroViewModel = pomodoroViewModel)

            }
        }
    }
}
@Composable
fun PomodoroApp(pomodoroViewModel: PomodoroViewModel) {
    PomodoroTheme {
        val navController: NavHostController = rememberNavController()
        NavGraph(navController = navController, pomodoroViewModel = pomodoroViewModel)
    }
}

