package com.laurentvrevin.pomodorotestapp.data.repository

import android.util.Log
import com.laurentvrevin.pomodorotestapp.data.preferences.PreferencesManager
import com.laurentvrevin.pomodorotestapp.domain.repository.TimerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerRepositoryImpl(val preferencesManager: PreferencesManager) : TimerRepository {

    private var initialWorkTime: Long = 25 * 60 // 25 minutes par défaut
    private var timeRemaining: Long = initialWorkTime

    private var job: Job? = null

    override suspend fun startTimer(workTime: Long, onFinish: () -> Unit) {
        job = CoroutineScope(Dispatchers.Main).launch {
            var timeRemaining = workTime
            while (timeRemaining > 0) {
                delay(1000L) // Attendre 1 seconde
                timeRemaining -= 1
                println("Time remaining: $timeRemaining seconds") // Log pour débogage
                if (timeRemaining <= 0) {
                    println("Timer finished") // Log pour débogage
                    onFinish()
                }
            }
        }
    }

    override suspend fun stopTimer() {
        job?.cancel()
        job = null
        Log.d("TAGTAG", "TimerRepository = Timer stopped")
    }


    override suspend fun resetTimer() {
        stopTimer()  // Arrête tout timer actif
        timeRemaining = initialWorkTime // Réinitialise à la valeur initiale
        Log.d("TAGTAG", "TimerRepository = Timer reset to $initialWorkTime seconds")
    }

    override fun loadWorkTime(): Long {
        return preferencesManager.getWorkTime()
    }

    override fun loadBreakTime(): Long {
        return preferencesManager.getBreakTime()
    }

    override fun saveWorkTime(workTime: Long) {
        preferencesManager.setWorkTime(workTime)
    }

    override fun saveBreakTime(breakTime: Long) {
        preferencesManager.setBreakTime(breakTime)
    }
}