package com.rizqanmr.movikucompose.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rizqanmr.movikucompose.data.MoviePagingSource
import com.rizqanmr.movikucompose.data.local.FavoriteDataStore
import com.rizqanmr.movikucompose.data.models.GenresItem
import com.rizqanmr.movikucompose.data.models.ItemMovieModel
import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.utils.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _genres = MutableStateFlow<List<GenresItem>>(emptyList())
    private val _favorites = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    private val _selectedGenreId = MutableStateFlow<Int?>(null)
    val selectedGenreId: StateFlow<Int?> get() = _selectedGenreId
    val genres: StateFlow<List<GenresItem>> get() = _genres

    init {
        getGenres()
        viewModelScope.launch {
            FavoriteDataStore.getAllFavoritesFlow(context).collect {
                _favorites.value = it
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            when (val result = movieRepository.getGenres()) {
                is Result.Success -> {
                    val list = result.data.genres.orEmpty()
                    _genres.value = list
                    if (list.isNotEmpty()) {
                        _selectedGenreId.value = list[0].id
                    }
                }
                is Result.Error -> {
                    _genres.value = emptyList()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val movies: Flow<PagingData<ItemMovieModel>> = _selectedGenreId
        .filterNotNull()
        .flatMapLatest { genreId ->
            Pager(PagingConfig(pageSize = 20, prefetchDistance = 5)) {
                MoviePagingSource(movieRepository, genreId)
            }.flow.cachedIn(viewModelScope)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun onGenreSelected(genreId: Int) {
        _selectedGenreId.value = genreId
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            FavoriteDataStore.toggleFavorite(context, movieId)
        }
    }

    fun isFavoriteFlow(movieId: Int): Flow<Boolean> {
        return FavoriteDataStore.getFavoriteMovieFlow(context, movieId)
    }
}