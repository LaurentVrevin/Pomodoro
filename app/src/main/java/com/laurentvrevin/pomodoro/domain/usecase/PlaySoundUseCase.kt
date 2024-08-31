package com.laurentvrevin.pomodoro.domain.usecase

import android.media.MediaPlayer

class PlaySoundUseCase(private val mediaPlayer: MediaPlayer) {
    fun execute() {
        mediaPlayer.start()
    }
}