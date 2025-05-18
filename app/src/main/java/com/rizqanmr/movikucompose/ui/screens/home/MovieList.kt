package com.rizqanmr.movikucompose.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.rizqanmr.movikucompose.data.models.ItemMovieModel

@Composable
fun MovieList(movies: LazyPagingItems<ItemMovieModel>) {
    LazyColumn {
        items(movies.itemCount) { index ->
            val item = movies[index]
            item?.let {
                Text(text = it.title.orEmpty())
            }
        }

        // Handling loading and error states
        when {
            movies.loadState.append is LoadState.Loading -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
            movies.loadState.refresh is LoadState.Error -> {
                val e = movies.loadState.refresh as LoadState.Error
                item {
                    Text(
                        text = "Error: ${e.error.localizedMessage}",
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}