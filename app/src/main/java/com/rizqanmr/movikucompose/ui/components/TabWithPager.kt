package com.rizqanmr.movikucompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rizqanmr.movikucompose.ui.theme.LightRed
import kotlinx.coroutines.launch

@Composable
fun TabWithPager() {
    val tabTitles = listOf("Home", "Profile", "Settings")
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    val coroutineScope = rememberCoroutineScope()
    val selectedTabColor = LightRed
    val unselectedTabColor = MaterialTheme.colorScheme.onSurfaceVariant
    val indicatorColor = LightRed

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = indicatorColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = indicatorColor
                )
            }
            ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
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

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            // Content for each page
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "This is ${tabTitles[page]} Page",
                    fontSize = 24.sp
                )
            }
        }
    }
}