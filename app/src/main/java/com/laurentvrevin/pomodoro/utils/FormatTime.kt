package com.laurentvrevin.pomodoro.utils

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun formatTime(time: Long): String {
    val minutes = time / 60
    val seconds = time % 60
    return String.format("%02d:%02d", minutes, seconds)
}