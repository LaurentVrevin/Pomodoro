package com.laurentvrevin.pomodoro.utils

import android.media.MediaPlayer
import com.laurentvrevin.pomodoro.data.repository.TimerRepositoryImpl
import com.laurentvrevin.pomodoro.domain.usecase.CalculateProgressUseCase
import com.laurentvrevin.pomodoro.domain.usecase.PlaySoundUseCase
import com.laurentvrevin.pomodoro.domain.usecase.ResetTimerUseCase
import com.laurentvrevin.pomodoro.domain.usecase.StartTimerUseCase
import com.laurentvrevin.pomodoro.domain.usecase.StopTimerUseCase
import com.laurentvrevin.pomodoro.presentation.viewmodel.PomodoroViewModel


//Créer une classe de prévisualisation de PomodoroViewModel qui étend PomodoroViewModel
//Ca permet d'éviter de recoder à chaque fois les instances de classes pour les Preview de screens si j'en avais plusieurs

class PreviewPomodoroViewModel : PomodoroViewModel(
    startTimerUseCase = StartTimerUseCase(TimerRepositoryImpl()),
    stopTimerUseCase = StopTimerUseCase(TimerRepositoryImpl()),
    resetTimerUseCase = ResetTimerUseCase(TimerRepositoryImpl()),
    calculateProgressUseCase = CalculateProgressUseCase(),
    playSoundUseCase = PlaySoundUseCase(MediaPlayer()), // MediaPlayer factice
    repository = TimerRepositoryImpl() // Repo factice
)