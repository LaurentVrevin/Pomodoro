package com.laurentvrevin.pomodorotestapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.laurentvrevin.pomodorotestapp.presentation.screens.BreakTimeScreen
import com.laurentvrevin.pomodorotestapp.presentation.screens.PomodoroScreen
import com.laurentvrevin.pomodorotestapp.presentation.screens.SettingsScreen
import com.laurentvrevin.pomodorotestapp.presentation.screens.TestScreen
import com.laurentvrevin.pomodorotestapp.presentation.screens.WorkTimeScreen
import com.laurentvrevin.pomodorotestapp.presentation.viewmodel.PomodoroViewModel


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
