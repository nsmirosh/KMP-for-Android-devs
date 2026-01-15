package com.learnkmp.newsapp.domain.repository

import com.learnkmp.newsapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSelectedCategory(): Flow<Category?>
    suspend fun saveSelectedCategory(category: Category?)
}
