package com.learnkmp.newsapp.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.networking.NewsDataRepo
import com.learnkmp.newsapp.networking.NewsDataRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.learnkmp.newsapp.domain.Category
import com.learnkmp.newsapp.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


const val categoryPrefsKey = "category"

class ArticleViewModel(val repo: NewsDataRepo, private val dataStore: DataStore<Preferences>) :
    ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles = _articles.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()


    init {
        viewModelScope.launch {
            val savedCategory = dataStore.data.map { data ->
                Category.entries.firstOrNull {
                    it.value == data[stringPreferencesKey(categoryPrefsKey)]
                }
            }.firstOrNull()
            savedCategory?.let {
                _selectedCategory.value = it
            }
            fetchArticles(savedCategory)
        }
    }

    fun onCategorySelected(category: Category?) {
        _selectedCategory.value = category
        viewModelScope.launch {
            dataStore.edit {
                it[stringPreferencesKey(categoryPrefsKey)] = category?.value.orEmpty()
            }
        }
        fetchArticles(category)
    }

    private fun fetchArticles(category: Category?) {
        viewModelScope.launch {
            when (val result = repo.getNewsData(category)) {
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