package com.rizqanmr.movikucompose.ui.screens.main

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun `performAction SwitchTheme should toggle dark theme`() {
        // Initially false
        assertEquals(false, viewModel.isDarkTheme.value)

        // Act - Toggle once (should become true)
        viewModel.performAction(MainViewModel.Action.SwitchTheme)
        assertEquals(true, viewModel.isDarkTheme.value)

        // Act - Toggle again (should become false)
        viewModel.performAction(MainViewModel.Action.SwitchTheme)
        assertEquals(false, viewModel.isDarkTheme.value)
    }
}