package com.learnkmp.newsapp.domain.usecase

import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCategoryUseCase(private val repository: SettingsRepository) {
    suspend operator fun invoke(): Result<Category?> {
        return repository.getSelectedCategory()
    }
}
