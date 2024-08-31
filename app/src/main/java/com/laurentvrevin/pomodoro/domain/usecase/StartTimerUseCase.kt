package com.laurentvrevin.pomodoro.domain.usecase

import com.laurentvrevin.pomodoro.domain.repository.TimerRepository

class StartTimerUseCase(private val timerRepository: TimerRepository) {
    suspend operator fun invoke(workTime: Long, onFinish: () -> Unit) {
        timerRepository.startTimer(workTime, onFinish)
    }
}