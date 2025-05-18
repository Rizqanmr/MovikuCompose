package com.rizqanmr.movikucompose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movikucompose.R
import com.rizqanmr.movikucompose.data.models.ItemMovieModel

@Composable
fun MovieItemCard(movie: ItemMovieModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.getUrlPoster())
                        .crossfade(true)
                        .build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(R.drawable.ic_broken_image),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                )

                Row(
                    modifier = Modifier
                        .height(20.dp)
                        .align(Alignment.BottomStart)
                        .background(Color.Gray.copy(alpha = 0.7f))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ) {
                    val starCount = movie.getRatingStar().toInt()
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < starCount) Color.Red else Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color.Red)
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = movie.getRating(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = movie.title.orEmpty(),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.getFormattedDate().orEmpty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}