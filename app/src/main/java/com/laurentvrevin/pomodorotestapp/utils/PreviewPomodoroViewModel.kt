package com.laurentvrevin.pomodorotestapp.utils


//Créer une classe de prévisualisation de PomodoroViewModel qui étend PomodoroViewModel
//Ca permet d'éviter de recoder à chaque fois les instances de classes pour les Preview de screens si j'en avais plusieurs

/*
class PreviewPomodoroViewModel : PomodoroViewModel(
    startTimerUseCase = StartTimerUseCase(TimerRepositoryImpl()),
    stopTimerUseCase = StopTimerUseCase(TimerRepositoryImpl()),
    resetTimerUseCase = ResetTimerUseCase(TimerRepositoryImpl()),
    calculateProgressUseCase = CalculateProgressUseCase(),
    playSoundUseCase = PlaySoundUseCase(MediaPlayer()), // MediaPlayer factice
    repository = TimerRepositoryImpl() // Repo factice
)*/