package com.learnkmp.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learnkmp.newsapp.domain.model.Article
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.usecase.GetArticlesUseCase
import com.learnkmp.newsapp.domain.usecase.GetSelectedCategoryUseCase
import com.learnkmp.newsapp.domain.usecase.SaveSelectedCategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getSelectedCategoryUseCase: GetSelectedCategoryUseCase,
    private val saveSelectedCategoryUseCase: SaveSelectedCategoryUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles = _articles.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        viewModelScope.launch {
            val savedCategory = getSelectedCategoryUseCase().first()
            _selectedCategory.value = savedCategory
            fetchArticles(savedCategory)
        }
    }

    fun onCategorySelected(category: Category?) {
        _selectedCategory.value = category
        viewModelScope.launch {
            saveSelectedCategoryUseCase(category)
        }
        fetchArticles(category)
    }

    private fun fetchArticles(category: Category?) {
        viewModelScope.launch {
            when (val result = getArticlesUseCase(category)) {
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
