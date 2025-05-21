package com.rizqanmr.movikucompose.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object FavoriteDataStore {
    private const val DATASTORE_NAME = "favorite_movies"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
    private val FAVORITE_KEY_PREFIX = "favorite_"

    fun getFavoriteMovieFlow(context: Context, movieId: Int): Flow<Boolean> {
        val key = booleanPreferencesKey("$FAVORITE_KEY_PREFIX$movieId")
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: false
        }
    }

    suspend fun toggleFavorite(context: Context, movieId: Int) {
        val key = booleanPreferencesKey("$FAVORITE_KEY_PREFIX$movieId")
        context.dataStore.edit { preferences ->
            val current = preferences[key] ?: false
            preferences[key] = !current
        }
    }

    fun getAllFavoritesFlow(context: Context): Flow<Map<Int, Boolean>> {
        return context.dataStore.data.map { preferences ->
            preferences.asMap()
                .filterKeys { it.name.startsWith(FAVORITE_KEY_PREFIX) }
                .mapNotNull { (key, value) ->
                    val movieId = key.name.removePrefix(FAVORITE_KEY_PREFIX).toIntOrNull()
                    val isFavorite = value as? Boolean
                    if (movieId != null && isFavorite != null) {
                        movieId to isFavorite
                    } else {
                        null
                    }
                }.toMap()
        }
    }
}