package com.rizqanmr.movikucompose.ui.screens.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rizqanmr.movikucompose.data.local.FavoriteDataStore
import com.rizqanmr.movikucompose.data.models.GenresItem
import com.rizqanmr.movikucompose.data.models.GenresModel
import com.rizqanmr.movikucompose.data.repository.MovieRepository
import com.rizqanmr.movikucompose.utils.network.Result
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: MovieRepository
    private lateinit var viewModel: HomeViewModel
    private lateinit var context: Context
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var testScope: TestScope

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        testScope = TestScope(testDispatcher)
        repository = mockk<MovieRepository>()
        context = mockk(relaxed = true)
        viewModel = HomeViewModel(repository, context)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getGenres success should update genres and selectedGenreId`() = testScope.runTest {
        // Given
        val genresList = listOf(GenresItem("Action", 1), GenresItem("Comedy", 35))
        coEvery { repository.getGenres() } returns Result.Success(GenresModel(genres = genresList))

        // When
        viewModel.getGenres()
        advanceUntilIdle()

        // Then
        assertEquals(genresList, viewModel.genres.value)
        assertEquals(1, viewModel.selectedGenreId.value)
    }

    @Test
    fun `getGenres error should return empty genres`() = testScope.runTest {
        // Given
        coEvery { repository.getGenres() } returns Result.Error("Something went wrong")

        // When
        viewModel.getGenres()
        advanceUntilIdle()

        // Then
        assertTrue(viewModel.genres.value.isEmpty())
    }

    @Test
    fun `onGenreSelected should update selectedGenreId`() = testScope.runTest {
        // When
        viewModel.onGenreSelected(99)

        // Then
        assertEquals(99, viewModel.selectedGenreId.value)
    }

    @Test
    fun `toggleFavorite should call FavoriteDataStore`() = testScope.runTest {
        mockkObject(FavoriteDataStore)
        coEvery { FavoriteDataStore.toggleFavorite(context, any()) } just Runs

        viewModel.toggleFavorite(42)
        advanceUntilIdle()

        coVerify { FavoriteDataStore.toggleFavorite(context, 42) }
    }

    @Test
    fun `isFavoriteFlow should delegate to FavoriteDataStore`() = testScope.runTest {
        mockkObject(FavoriteDataStore)
        val expectedFlow = flowOf(true)
        every { FavoriteDataStore.getFavoriteMovieFlow(context, 42) } returns expectedFlow

        val result = viewModel.isFavoriteFlow(42)
        assertEquals(expectedFlow, result)
    }
}