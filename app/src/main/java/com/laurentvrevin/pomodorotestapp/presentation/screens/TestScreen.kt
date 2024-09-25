package com.laurentvrevin.pomodorotestapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.laurentvrevin.pomodorotestapp.R


@Composable
fun TestScreen() {
    // Charger la composition de l'animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.flow))

    // Gérer la progression de l'animation avec un état réactif
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever // Répéter l'animation en boucle
    )

    // Afficher l'animation dans une Box avec taille maximale
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }, // Passer la lambda avec la progression
            modifier = Modifier.fillMaxSize()
        )
    }
}
