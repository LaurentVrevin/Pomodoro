package com.laurentvrevin.pomodoro.data.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("pomodoro_prefs", Context.MODE_PRIVATE)

    fun getWorkTime(): Long {
        return sharedPreferences.getLong("work_time", 25 * 60L)  // Valeur par défaut : 25 minutes
    }

    fun getBreakTime(): Long {
        return sharedPreferences.getLong("break_time", 5 * 60L)  // Valeur par défaut : 5 minutes
    }

    fun setWorkTime(workTime: Long) {
        sharedPreferences.edit().putLong("work_time", workTime).apply()
    }

    fun setBreakTime(breakTime: Long) {
        sharedPreferences.edit().putLong("break_time", breakTime).apply()
    }
}