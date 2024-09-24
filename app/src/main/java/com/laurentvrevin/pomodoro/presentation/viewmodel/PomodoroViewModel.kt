package com.laurentvrevin.pomodoro.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laurentvrevin.pomodoro.domain.repository.TimerRepository
import com.laurentvrevin.pomodoro.domain.usecase.CalculateProgressUseCase
import com.laurentvrevin.pomodoro.domain.usecase.PlaySoundUseCase
import com.laurentvrevin.pomodoro.domain.usecase.ResetTimerUseCase
import com.laurentvrevin.pomodoro.domain.usecase.StartTimerUseCase
import com.laurentvrevin.pomodoro.domain.usecase.StopTimerUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.max

open class PomodoroViewModel(
    private val startTimerUseCase: StartTimerUseCase,
    private val stopTimerUseCase: StopTimerUseCase,
    private val resetTimerUseCase: ResetTimerUseCase,
    private val calculateProgressUseCase: CalculateProgressUseCase,
    private val playSoundUseCase: PlaySoundUseCase,
    private val repository: TimerRepository
) : ViewModel() {

    private var initialWorkTime = 25L * 60L
    private var initialBreakTime = 5L * 60L
    private val _worktime = MutableStateFlow(initialWorkTime)
    val worktime: StateFlow<Long> = _worktime
    private val _breaktime = MutableStateFlow(initialBreakTime)
    val breaktime: StateFlow<Long> = _breaktime

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private val _progress = MutableStateFlow(1f)  // 100% au début
    val progress: StateFlow<Float> = _progress

    private var job: Job? = null

    fun updateWorkTime(newWorkTime: Long) {
        repository.saveWorkTime(newWorkTime)
        initialWorkTime = newWorkTime
    }

    fun updateBreakTime(newBreakTime: Long) {
        repository.saveBreakTime(newBreakTime)
        initialBreakTime = newBreakTime
    }

    // Utilisation du StartTimerUseCase pour démarrer le timer
    private fun startTimer() {
        job = viewModelScope.launch {
            _isRunning.value = true
            startTimerUseCase(_worktime.value) {  // Utilisation du use case
                _isRunning.value = false
                playSoundUseCase.execute()  // Quand le timer est fini
            }

            // Mise à jour régulière de la progression
            while (_worktime.value > 0) {
                delay(1000L)
                _worktime.value = max(_worktime.value - 1, 0)
                _progress.value = calculateProgressUseCase.execute(_worktime.value, initialWorkTime)
                if (_worktime.value == 0L) {
                    _isRunning.value = false
                    playSoundUseCase.execute()
                    break
                }
            }
        }
    }

    private fun stopTimer() {
        viewModelScope.launch {
            stopTimerUseCase()  // Appel au use case StopTimer
            job?.cancel()
            job = null
            _isRunning.value = false
            Log.d("PomodoroViewModel", "Timer stopped")
        }
    }

    fun resetTimer() {
        viewModelScope.launch {
            stopTimer() // Stop le timer
            _worktime.value = initialWorkTime // Réinitialiser le temps de travail
            resetTimerUseCase()  // Appel au use case ResetTimer
            _isRunning.value = false
            _progress.value = 1f
            Log.d("PomodoroViewModel", "Timer reset")
        }
    }

    fun toggleTimer(workTime: Long) {
        if (_isRunning.value) {
            stopTimer()
        } else {
            _worktime.value = workTime
            startTimer()  // Utilisation de la méthode mise à jour qui appelle le use case
        }
    }
}