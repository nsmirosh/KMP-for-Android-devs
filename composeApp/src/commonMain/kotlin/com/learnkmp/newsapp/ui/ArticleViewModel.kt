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

private val fakeArticles = listOf(
    Article(
        source = "Alice Johnson",
        pubDate = "2025-01-05 12:15:00",
        title = "The Rise of Kotlin Multiplatform in Mobile Development",
        link = "https://example.com/kotlin-multiplatform",
        imageUrl = "https://picsum.photos/seed/1/300/200",
        keywords = listOf("KMP", "Mobile", "Trends")
    ),
    Article(
        source = "Bob Smith",
        pubDate = "2025-01-05 13:15:00",
        title = "Exploring Clean Architecture in Android",
        link = "https://example.com/clean-architecture-android",
        imageUrl = "https://picsum.photos/seed/2/300/200",
        keywords = listOf("Android", "Architecture")
    ),
    Article(
        source = "Charlotte Brown",
        pubDate = "2025-01-05 14:15:00",
        title = "How to Effectively Test Asynchronous Code in Android",
        link = "https://example.com/testing-async-android",
        imageUrl = "https://picsum.photos/seed/3/300/200",
        keywords = listOf("Testing", "Async", "Android")
    ),
    Article(
        source = "David Lee",
        pubDate = "2025-01-05 15:15:00",
        title = "Top 5 Jetpack Compose Libraries to Know in 2025",
        link = "https://example.com/jetpack-compose-libraries",
        imageUrl = "https://picsum.photos/seed/4/300/200",
        keywords = listOf("Compose", "Libraries")
    ),
    Article(
        source = "Emily Davis",
        pubDate = "2025-01-05 16:15:00",
        title = "Understanding Dependency Injection with Koin",
        link = "https://example.com/dependency-injection-koin",
        imageUrl = "https://picsum.photos/seed/5/300/200",
        keywords = listOf("DI", "Koin")
    ),
    Article(
        source = "Frank Wilson",
        pubDate = "2025-01-10 11:45:00",
        title = "Mastering Coroutine Flows for Reactive Android Apps",
        link = "https://example.com/coroutine-flows",
        imageUrl = "https://picsum.photos/seed/6/300/200",
        keywords = listOf("Coroutines", "Flows")
    ),
    Article(
        source = "Grace Thomas",
        pubDate = "2025-01-05 12:15:00",
        title = "Introduction to Voyager for State Management in KMP",
        link = "https://example.com/voyager-state-management",
        imageUrl = "https://picsum.photos/seed/7/300/200",
        keywords = listOf("KMP", "State", "Voyager")
    ),
    Article(
        source = "Henry Moore",
        pubDate = "2025-01-05 10:15:00",
        title = "Optimizing Performance in KMP Projects",
        link = "https://example.com/optimizing-kmp-performance",
        imageUrl = "https://picsum.photos/seed/8/300/200",
        keywords = listOf("Performance", "KMP")
    ),

    Article(
        source = "Ivy Martinez",
        pubDate = "2025-01-05 12:15:00",
        title = "Advanced Logging Techniques with Kermit in KMP",
        link = "https://example.com/kermit-logging-kmp",
        imageUrl = "https://picsum.photos/seed/9/300/200",
        keywords = listOf("Logging", "Kermit")
    ),
    Article(
        source = "Jack Anderson",
        pubDate = "2025-01-05 12:15:00",
        title = "Building Accessible UIs with Compose Multiplatform",
        link = "https://example.com/accessible-ui-compose",
        imageUrl = "https://picsum.photos/seed/10/300/200",
        keywords = listOf("Accessibility", "Compose")
    ),
    Article(
        source = "Karen Phillips",
        pubDate = "2025-01-05 12:15:00",
        title = "Best Practices for Error Handling in KMP Applications",
        link = "https://example.com/error-handling-kmp",
        imageUrl = "https://picsum.photos/seed/11/300/200",
        keywords = listOf("Errors", "Best Practices")
    )
)


const val categoryPrefsKey = "category"

class ArticleViewModel(val repo: NewsDataRepo, private val dataStore: DataStore<Preferences>) :
    ViewModel() {

    private val _articles = MutableStateFlow(fakeArticles)
    val articles = _articles.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()


    init {
        viewModelScope.launch{
            val savedCategory = dataStore.data.map { data ->
                Category.entries.firstOrNull {
                    it.value == data[stringPreferencesKey(categoryPrefsKey)]
                }
            }.firstOrNull()
            savedCategory?.let {
                _selectedCategory.value = it
            }
            println("savedCategory = $savedCategory")
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