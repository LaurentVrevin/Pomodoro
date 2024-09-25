package com.laurentvrevin.pomodorotestapp.domain.usecase

class CalculateProgressUseCase {
    fun execute(currentTime: Long, initialTime: Long): Float {
        return currentTime / initialTime.toFloat()
    }
}