package com.learnkmp.newsapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(private val dataStore: DataStore<Preferences>) : SettingsRepository {

    private val categoryPrefsKey = stringPreferencesKey("category")

    override fun getSelectedCategory(): Flow<Category?> {
        return dataStore.data.map { preferences ->
            val categoryValue = preferences[categoryPrefsKey]
            Category.entries.find { it.value == categoryValue }
        }
    }

    override suspend fun saveSelectedCategory(category: Category?) {
        dataStore.edit { preferences ->
            if (category == null) {
                preferences.remove(categoryPrefsKey)
            } else {
                preferences[categoryPrefsKey] = category.value
            }
        }
    }
}
