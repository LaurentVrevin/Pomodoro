package com.laurentvrevin.pomodoro.presentation.components

// Import des différentes bibliothèques nécessaires
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.math.abs

// Fonction pour créer un état pour le Picker, qui stockera l'élément sélectionné.
// mutableStateOf("") crée un état mutable qui est initialisé à une chaîne vide.
@Composable
fun rememberPickerState() = remember { mutableStateOf("") }

@OptIn(ExperimentalFoundationApi::class) // Utilisation d'une API expérimentale (Snap Fling Behavior)
@Composable
fun Picker(
    items: List<String>, // Liste des éléments à afficher dans le Picker
    selectedItem: MutableState<String> = rememberPickerState(), // L'état de l'élément sélectionné
    modifier: Modifier = Modifier, // Modificateur pour personnaliser le style
    startIndex: Int = 0, // L'index de départ (élément initial à afficher)
    visibleItemsCount: Int = 3, // Le nombre d'éléments visibles à la fois dans le Picker
    label: String = "", // Le label à afficher au-dessus de la liste
    textStyle: TextStyle = LocalTextStyle.current.copy(fontSize = 20.sp), // Style du texte avec une taille par défaut de 20.sp
    dividerColor: Color = LocalContentColor.current, // Couleur des diviseurs (ligne horizontale)
) {
    // Calcul pour obtenir l'index de l'élément au milieu de la liste visible
    val visibleMiddleIndex = visibleItemsCount / 2

    // Création d'un état pour la liste, qui simule un très grand nombre d'éléments.
    // Cela permet d'avoir une liste qui semble infinie en utilisant des multiples des items.
    val listState = rememberLazyListState(startIndex + items.size * 1000)

    // LaunchedEffect est un composable qui exécute un bloc de code à chaque changement
    // de listState. Ici, il réagit lorsque l'utilisateur fait défiler la liste.
    LaunchedEffect(listState) {
        // snapshotFlow capture le premier élément visible dans la liste (l'index de départ).
        snapshotFlow { listState.firstVisibleItemIndex + visibleMiddleIndex }
            // On utilise map pour obtenir l'élément réel dans la liste
            .map { index -> items[index % items.size] } // On s'assure de ne pas dépasser la taille de la liste en utilisant %.
            // distinctUntilChanged évite les collect inutiles si l'élément sélectionné ne change pas
            .distinctUntilChanged()
            // collect met à jour l'élément sélectionné en fonction de l'index calculé.
            .collect { selectedItem.value = it }
    }

    // Réorganisation du Box pour placer le texte du label au-dessus de la LazyColumn
    Box(
        modifier = Modifier
            .background(color = Color.Green)
            .width(80.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centrer les éléments horizontalement dans la colonne
            modifier = Modifier.wrapContentWidth() // Occuper toute la largeur disponible
        ) {
            // Affichage du label au-dessus du Picker
            Text(
                text = label, // Le texte du label
                style = textStyle.copy(fontSize = 16.sp), // Style légèrement plus petit pour le label
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(color = Color.Red) // Fond rouge pour le label
                    .align(Alignment.CenterHorizontally) // Centrer le texte dans la colonne
            )

            // Espacement entre le label et le Picker
            Spacer(modifier = Modifier.height(16.dp))

            // LazyColumn pour afficher la liste des éléments
            LazyColumn(
                state = listState, // L'état de la liste (pour savoir quels éléments sont visibles)
                flingBehavior = rememberSnapFlingBehavior(listState), // Snap au scrolling pour s'arrêter précisément sur un élément
                contentPadding = PaddingValues(vertical = 16.dp), // Padding pour espacer un peu la liste des bords
                horizontalAlignment = Alignment.CenterHorizontally, // Centrer les éléments dans la colonne
                modifier = Modifier
                    .height((visibleItemsCount * 40).dp) // La hauteur du Picker dépend du nombre d'éléments visibles
                    .wrapContentWidth()
                    .background(color = Color.Yellow) // Fond jaune pour le Picker
            ) {
                // Génération d'une grande liste d'éléments à partir de la taille réelle des items
                items(items.size * 2000) { index ->

                    // Calcul de la distance par rapport à l'élément central
                    val distanceToCenter = abs(index - (remember { derivedStateOf { listState.firstVisibleItemIndex + visibleMiddleIndex }}.value))

                    // Ajustement de l'opacité en fonction de la distance
                    val opacity = when (distanceToCenter) {
                        0 -> 1f // L'élément au centre est entièrement opaque
                        1 -> 0.7f // Les éléments voisins sont légèrement transparents
                        else -> 0.4f // Les autres éléments sont encore plus transparents
                    }
                    Text(
                        text = items[index % items.size].padStart(2,'0'), // Récupérer l'élément en utilisant l'index modulo la taille de la liste
                        maxLines = 1, // Un seul ligne par élément pour le texte
                        overflow = TextOverflow.Ellipsis, // Si le texte dépasse, il sera coupé avec des points
                        style = textStyle, // Appliquer le style défini plus haut (taille du texte, couleur, etc.)
                        modifier = Modifier
                            .padding(vertical = 8.dp) // Padding vertical pour espacer un peu les éléments
                            .graphicsLayer(alpha = opacity)
                    )
                }
            }

            // Ajout d'un diviseur (ligne horizontale) au centre du Picker
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth() // Le diviseur occupe toute la largeur du Picker
                    .padding(vertical = 0.dp), // Ajouter du padding si nécessaire
                color = Color.Green // La couleur du diviseur est verte ici, mais peut être modifiée
            )
        }
    }
}