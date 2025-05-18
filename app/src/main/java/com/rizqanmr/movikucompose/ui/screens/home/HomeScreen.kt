package com.rizqanmr.movikucompose.ui.screens.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizqanmr.movikucompose.ui.screens.main.MainViewModel
import com.rizqanmr.movikucompose.ui.screens.main.TopAppBar

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
    @StringRes titleResource: Int
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                titleResource = titleResource,
                onThemeSwitch = {
                    viewModel.performAction(MainViewModel.Action.SwitchTheme)
                }
            )
            TabWithPager()
        }
    }
}