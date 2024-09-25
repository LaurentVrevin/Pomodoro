package com.laurentvrevin.pomodorotestapp.domain.usecase

import com.laurentvrevin.pomodorotestapp.domain.repository.TimerRepository

class StartTimerUseCase(private val timerRepository: TimerRepository) {
    suspend operator fun invoke(workTime: Long, onFinish: () -> Unit) {
        timerRepository.startTimer(workTime, onFinish)
    }
}