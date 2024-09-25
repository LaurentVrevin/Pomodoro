package com.laurentvrevin.pomodorotestapp.domain.repository

interface TimerRepository {
    suspend fun startTimer(workTime: Long, onFinish: () -> Unit)
    suspend fun stopTimer()
    suspend fun resetTimer()

    fun loadWorkTime(): Long
    fun loadBreakTime(): Long
    fun saveWorkTime(workTime: Long)
    fun saveBreakTime(breakTime: Long)
}