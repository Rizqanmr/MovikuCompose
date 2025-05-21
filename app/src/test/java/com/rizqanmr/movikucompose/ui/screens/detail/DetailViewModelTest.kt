package com.rizqanmr.movikucompose.ui.screens.detail

import com.rizqanmr.movikucompose.data.models.DetailMovieModel
import com.rizqanmr.movikucompose.data.models.GenresItem
import com.rizqanmr.movikucompose.data.models.Videos
import com.rizqanmr.movikucompose.data.models.VideosItem
import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.utils.network.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var repository: MovieRepository

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = DetailViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDetailMovie success should update detailMovie and set isLoading false`(): Unit = testScope.runTest {
        // Given
        val dummyMovie = fakeDetailMovie()
        coEvery { repository.getDetailMovie(1) } returns Result.Success(dummyMovie)

        // When
        viewModel.getDetailMovie(1)
        advanceUntilIdle()

        // Then
        assertEquals(dummyMovie, viewModel.detailMovie.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(null, viewModel.errorMessage.value)
    }

    @Test
    fun `getDetailMovie error should update errorMessage and set isLoading false`() = testScope.runTest {
        // Given
        val errorMessage = "Movie not found"
        coEvery { repository.getDetailMovie(999) } returns Result.Error(errorMessage)

        // When
        viewModel.getDetailMovie(999)
        advanceUntilIdle()

        // Then
        assertEquals(null, viewModel.detailMovie.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(errorMessage, viewModel.errorMessage.value)
    }

    companion object {
        fun fakeDetailMovie(): DetailMovieModel =  DetailMovieModel(
            originalLanguage = "en",
            imdbId = "tt4154756",
            videos = dummyVideos, // atau isi dengan dummyVideos jika ingin
            video = false,
            title = "Avengers: Infinity War",
            backdropPath = "/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
            revenue = 2048359754,
            genres = listOf(
                GenresItem(name = "Action", id = 28),
                GenresItem(name = "Adventure", id = 12)
            ),
            popularity = 123.456,
            id = 299536,
            voteCount = 23782,
            budget = 321000000,
            overview = "As the Avengers and their allies have continued to protect the world...",
            originalTitle = "Avengers: Infinity War",
            runtime = 149,
            posterPath = "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
            releaseDate = "2018-04-25",
            voteAverage = 8.3,
            tagline = "An entire universe. Once and for all.",
            adult = false,
            homepage = "https://www.marvel.com/movies/avengers-infinity-war",
            status = "Released"
        )

        val dummyVideos = Videos(
            results = listOf(
                VideosItem(
                    id = "abc123",
                    key = "6ZfuNTqbHE8",
                    name = "Official Trailer",
                    site = "YouTube",
                    type = "Trailer"
                )
            )
        )
    }
}