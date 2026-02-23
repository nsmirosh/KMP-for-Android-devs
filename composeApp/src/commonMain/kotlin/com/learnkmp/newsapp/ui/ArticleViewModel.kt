package com.learnkmp.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learnkmp.newsapp.domain.models.Article
import com.learnkmp.newsapp.domain.models.Category
import com.learnkmp.newsapp.domain.models.Result
import com.learnkmp.newsapp.domain.repositories.NewsRepository
import com.learnkmp.newsapp.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ArticleViewModel(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles = _articles.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = settingsRepository.getSelectedCategory()) {
                is Result.Success -> {
                    val savedCategory = result.data
                    savedCategory.let {
                        _selectedCategory.value = it
                    }
                    fetchArticles(savedCategory)
                }

                is Result.Error -> {
                    println("Error: ${result.throwable.message}")
                }
            }
        }
    }

    fun onCategorySelected(category: Category?) {
        _selectedCategory.value = category
        viewModelScope.launch {
            settingsRepository.saveSelectedCategory(category)
        }
        fetchArticles(category)
    }

    private fun fetchArticles(category: Category?) {
        viewModelScope.launch {
            when (val result = newsRepository.getNewsData(category)) {
                is Result.Success -> {
                    _articles.value = result.data
                }

                is Result.Error -> {
                    println("Error: ${result.throwable.message}")

                }
            }
        }
    }
}