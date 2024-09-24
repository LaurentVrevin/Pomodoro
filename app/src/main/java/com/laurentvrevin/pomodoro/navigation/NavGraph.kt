package com.laurentvrevin.pomodoro.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.laurentvrevin.pomodoro.presentation.screens.BreakTimeScreen
import com.laurentvrevin.pomodoro.presentation.screens.PomodoroScreen
import com.laurentvrevin.pomodoro.presentation.screens.SettingsScreen
import com.laurentvrevin.pomodoro.presentation.screens.TestScreen
import com.laurentvrevin.pomodoro.presentation.screens.WorkTimeScreen
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel


@Composable
fun NavGraph(navController: NavHostController, pomodoroViewModel: PomodoroViewModel) {
    NavHost(
        navController = navController,
        startDestination = "TestScreen"
    ) {
        composable("TestScreen") {
            TestScreen()
        }

        composable("WorkTimeScreen") {
            WorkTimeScreen(navController = navController, pomodoroViewModel = pomodoroViewModel)
        }

        composable("BreakTimeScreen") {
            BreakTimeScreen(navController = navController, pomodoroViewModel = pomodoroViewModel)
        }

        composable("PomodoroScreen") {
            PomodoroScreen(navController = navController, pomodoroViewModel = pomodoroViewModel)
        }

        composable("Settings") {
            SettingsScreen(navController = navController, pomodoroViewModel = pomodoroViewModel)
        }
    }
}
