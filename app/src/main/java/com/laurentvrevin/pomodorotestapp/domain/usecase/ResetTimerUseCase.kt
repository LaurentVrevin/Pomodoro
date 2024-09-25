package com.laurentvrevin.pomodorotestapp.domain.usecase

import com.laurentvrevin.pomodorotestapp.domain.repository.TimerRepository

class ResetTimerUseCase(private val timerRepository: TimerRepository) {
    suspend operator fun invoke() {
        timerRepository.resetTimer()
    }
}