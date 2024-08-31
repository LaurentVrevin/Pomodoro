package com.laurentvrevin.pomodoro.domain.usecase

import com.laurentvrevin.pomodoro.domain.repository.TimerRepository

class StopTimerUseCase(private val timerRepository: TimerRepository) {
    suspend operator fun invoke() {
        timerRepository.stopTimer()
    }
}