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
import com.laurentvrevin.pomodoro.presentation.screens.PomodoroScreen
import com.laurentvrevin.pomodoro.presentation.screens.SettingsScreen
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel


@Composable
fun NavGraph(navController: NavHostController, pomodoroViewModel: PomodoroViewModel){
    NavHost(
        navController = navController,
        startDestination = "Main"
    ) {
        composable("Main") {
            PomodoroScreen(
                navController = navController,
                pomodoroViewModel = pomodoroViewModel)
        }

        composable("Settings") {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { -1000 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { 1000 }) + fadeOut()
                ) {

                SettingsScreen(
                    navController = navController,
                    pomodoroViewModel = pomodoroViewModel)
            }

        }
    }

}
