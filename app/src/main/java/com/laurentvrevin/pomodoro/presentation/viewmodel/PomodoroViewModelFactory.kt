package com.laurentvrevin.pomodoro.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.media.MediaPlayer
import com.laurentvrevin.pomodoro.R
import com.laurentvrevin.pomodoro.domain.repository.TimerRepository
import com.laurentvrevin.pomodoro.domain.usecase.CalculateProgressUseCase
import com.laurentvrevin.pomodoro.domain.usecase.PlaySoundUseCase
import com.laurentvrevin.pomodoro.domain.usecase.ResetTimerUseCase
import com.laurentvrevin.pomodoro.domain.usecase.StartTimerUseCase
import com.laurentvrevin.pomodoro.domain.usecase.StopTimerUseCase

class PomodoroViewModelFactory(
    private val application: Application,
    private val repository: TimerRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PomodoroViewModel::class.java)) {
            val mediaPlayer = MediaPlayer.create(application, R.raw.alarm02)
            return PomodoroViewModel(
                StartTimerUseCase(repository),
                StopTimerUseCase(repository),
                ResetTimerUseCase(repository),
                CalculateProgressUseCase(),
                PlaySoundUseCase(mediaPlayer),
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
