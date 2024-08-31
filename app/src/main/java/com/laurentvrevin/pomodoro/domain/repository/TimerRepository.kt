package com.laurentvrevin.pomodoro.domain.repository

interface TimerRepository {
    suspend fun startTimer(workTime: Long, onFinish: () -> Unit)
    suspend fun stopTimer()
    fun resetTimer()

}