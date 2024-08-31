package com.laurentvrevin.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.laurentvrevin.pomodoro.data.repository.TimerRepositoryImpl
import com.laurentvrevin.pomodoro.presentation.screens.PomodoroScreen
import com.laurentvrevin.pomodoro.presentation.theme.PomodoroTheme
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Instance le repository qu'on passe à la Factory
        val repository by lazy { TimerRepositoryImpl() }

        // Instance le ViewModel en utilisant la Factory
        val pomodoroViewModel: PomodoroViewModel by viewModels {
            PomodoroViewModelFactory(application, repository)
        }
            
        setContent {
            PomodoroTheme {

                PomodoroScreen(pomodoroViewModel = pomodoroViewModel)

            }
        }
    }
}

