package com.rizqanmr.movikucompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqanmr.movikucompose.data.models.GenresItem
import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.utils.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val homeState = MutableStateFlow<HomeState>(HomeState.StartState)
    private val _genres = MutableStateFlow<List<GenresItem>>(emptyList())
    val genres: StateFlow<List<GenresItem>> = _genres.asStateFlow()

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            when (val result = movieRepository.getGenres()) {
                is Result.Success -> {
                    _genres.value = result.data.genres.orEmpty()
                }
                is Result.Error -> {
                    _genres.value = emptyList()
                }
            }
        }
    }
}