package com.rizqanmr.movikucompose.ui.screens.home

import androidx.paging.PagingData
import com.rizqanmr.movikucompose.data.models.GenresItem

sealed class HomeState {
    data object StartState : HomeState()
    data object LoadingState : HomeState()
    data class SuccessState(val data: PagingData<GenresItem>) : HomeState()
    data class ErrorState(val message: String) : HomeState()
}