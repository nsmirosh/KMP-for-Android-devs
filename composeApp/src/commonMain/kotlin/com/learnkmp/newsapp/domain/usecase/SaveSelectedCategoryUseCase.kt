package com.learnkmp.newsapp.domain.usecase

import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.repository.SettingsRepository

class SaveSelectedCategoryUseCase(private val repository: SettingsRepository) {
    suspend operator fun invoke(category: Category?) {
        repository.saveSelectedCategory(category)
    }
}
