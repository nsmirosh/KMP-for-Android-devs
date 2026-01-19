package com.learnkmp.newsapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(private val dataStore: DataStore<Preferences>) : SettingsRepository {

    private val categoryPrefsKey = stringPreferencesKey("category")

    override suspend fun getSelectedCategory(): Result<Category?> {
        val result = dataStore.data
            .map<Preferences, Result<Category?>> { preferences ->
                val categoryValue = preferences[categoryPrefsKey]
                Result.Success(Category.entries.find { it.value == categoryValue })
            }
            .catch { emit(Result.Error(it)) }
        return result.first()
    }

    override suspend fun saveSelectedCategory(category: Category?): Result<Unit> {
        return try {
            dataStore.edit { preferences ->
                if (category == null) {
                    preferences.remove(categoryPrefsKey)
                } else {
                    preferences[categoryPrefsKey] = category.value
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
