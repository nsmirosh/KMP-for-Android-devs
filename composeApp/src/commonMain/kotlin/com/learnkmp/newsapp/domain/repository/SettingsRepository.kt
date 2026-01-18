package com.learnkmp.newsapp.domain.repository

import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSelectedCategory(): Flow<Result<Category?>>
    suspend fun saveSelectedCategory(category: Category?): Result<Unit>
}
