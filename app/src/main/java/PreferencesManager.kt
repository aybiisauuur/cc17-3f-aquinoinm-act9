package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create DataStore instance
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "flight_preferences")

// PreferencesManager for managing the search query
class PreferencesManager(private val context: Context) {

    private val SEARCH_QUERY_KEY = stringPreferencesKey("search_query")

    // Save search query
    suspend fun saveSearchQuery(query: String) {
        context.dataStore.edit { preferences ->
            preferences[SEARCH_QUERY_KEY] = query
        }
    }

    // Retrieve search query as a Flow
    val searchQueryFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[SEARCH_QUERY_KEY] }
}
