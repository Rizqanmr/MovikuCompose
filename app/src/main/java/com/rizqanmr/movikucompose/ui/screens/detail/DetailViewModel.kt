package com.rizqanmr.movikucompose.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizqanmr.movikucompose.data.models.DetailMovieModel
import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.utils.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _detailMovie = MutableStateFlow<DetailMovieModel?>(null)
    val detailMovie: StateFlow<DetailMovieModel?> = _detailMovie

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = repository.getDetailMovie(movieId)) {
                is Result.Success -> {
                    _detailMovie.value = result.data
                }
                is Result.Error -> {
                    _errorMessage.value = result.errorMessage
                }
            }
            _isLoading.value = false
        }
    }
}