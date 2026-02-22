package com.learnkmp.newsapp.domain.repositories

import com.learnkmp.newsapp.domain.models.Category
import com.learnkmp.newsapp.domain.models.Result


interface SettingsRepository {
    suspend fun getSelectedCategory(): Result<Category?>
    suspend fun saveSelectedCategory(category: Category?): Result<Unit>
}




