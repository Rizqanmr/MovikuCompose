package com.rizqanmr.movikucompose.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rizqanmr.movikucompose.data.models.ItemMovieModel

@Composable
fun DetailScreen(
    navController: NavController
) {
    val movie = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<ItemMovieModel>("movie")

    if (movie != null) {
        MovieDetailContent(movie = movie, navController) {
            navController.popBackStack()
        }
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Movie not found")
        }
    }
}