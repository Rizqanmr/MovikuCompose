package com.rizqanmr.movikucompose.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.rizqanmr.movikucompose.ui.components.ShimmerTab
import com.rizqanmr.movikucompose.ui.theme.LightRed
import kotlinx.coroutines.launch

@Composable
fun TabWithPager(viewModel: HomeViewModel = hiltViewModel()) {
    val genres by viewModel.genres.collectAsState()
    val movies = viewModel.movies.collectAsLazyPagingItems()

    val pagerState = rememberPagerState(pageCount = { genres.size })
    val coroutineScope = rememberCoroutineScope()
    val selectedTabColor = LightRed
    val unselectedTabColor = MaterialTheme.colorScheme.onSurfaceVariant
    val indicatorColor = LightRed

    Column {
        if (genres.isEmpty()) {
            ScrollableTabRow(
                selectedTabIndex = 0,
                edgePadding = 0.dp,
                indicator = {},
                divider = {}
            ) {
                repeat(5) {
                    Tab(
                        selected = false,
                        onClick = {},
                        text = { ShimmerTab() }
                    )
                }
            }
        } else {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                edgePadding = 0.dp,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = indicatorColor,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = indicatorColor
                    )
                }
            ) {
                genres.forEachIndexed { index, genre ->
                    Tab(
                        text = {
                            Text(
                                text = genre.name.orEmpty(),
                                color = if (pagerState.currentPage == index) selectedTabColor else unselectedTabColor
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            // Scroll to the selected page when tab is clicked
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Content for each page
            val genre = genres.getOrNull(page)
            genre?.let {
                viewModel.onGenreSelected(it.id ?: 0)
                MovieList(movies = movies)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "This is ${genres[page].name.orEmpty()} Page (ID: ${genres[page].id})",
                    fontSize = 24.sp
                )
            }
        }
    }
}