package com.learnkmp.newsapp.ui

import androidx.lifecycle.ViewModel
import com.learnkmp.newsapp.models.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private val fakeArticles = listOf(
    Article(
        id = 1,
        author = "Alice Johnson",
        publishedAt = "2025-01-05 12:15:00",
        title = "The Rise of Kotlin Multiplatform in Mobile Development",
        url = "https://example.com/kotlin-multiplatform",
        imageUrl = "https://picsum.photos/seed/1/300/200",
        keywords = listOf("KMP", "Mobile", "Trends")
    ),
    Article(
        id = 2,
        author = "Bob Smith",
        publishedAt = "2025-01-05 13:15:00",
        title = "Exploring Clean Architecture in Android",
        url = "https://example.com/clean-architecture-android",
        imageUrl = "https://picsum.photos/seed/2/300/200",
        keywords = listOf("Android", "Architecture")
    ),
    Article(
        id = 3,
        author = "Charlotte Brown",
        publishedAt = "2025-01-05 14:15:00",
        title = "How to Effectively Test Asynchronous Code in Android",
        url = "https://example.com/testing-async-android",
        imageUrl = "https://picsum.photos/seed/3/300/200",
        keywords = listOf("Testing", "Async", "Android")
    ),
    Article(
        id = 4,
        author = "David Lee",
        publishedAt = "2025-01-05 15:15:00",
        title = "Top 5 Jetpack Compose Libraries to Know in 2025",
        url = "https://example.com/jetpack-compose-libraries",
        imageUrl = "https://picsum.photos/seed/4/300/200",
        keywords = listOf("Compose", "Libraries")
    ),
    Article(
        id = 5,
        author = "Emily Davis",
        publishedAt = "2025-01-05 16:15:00",
        title = "Understanding Dependency Injection with Koin",
        url = "https://example.com/dependency-injection-koin",
        imageUrl = "https://picsum.photos/seed/5/300/200",
        keywords = listOf("DI", "Koin")
    ),
    Article(
        id = 6,
        author = "Frank Wilson",
        publishedAt = "2025-01-10 11:45:00",
        title = "Mastering Coroutine Flows for Reactive Android Apps",
        url = "https://example.com/coroutine-flows",
        imageUrl = "https://picsum.photos/seed/6/300/200",
        keywords = listOf("Coroutines", "Flows")
    ),
    Article(
        id = 7,
        author = "Grace Thomas",
        publishedAt = "2025-01-05 12:15:00",
        title = "Introduction to Voyager for State Management in KMP",
        url = "https://example.com/voyager-state-management",
        imageUrl = "https://picsum.photos/seed/7/300/200",
        keywords = listOf("KMP", "State", "Voyager")
    ),
    Article(
        id = 8,
        author = "Henry Moore",
        publishedAt = "2025-01-05 10:15:00",
        title = "Optimizing Performance in KMP Projects",
        url = "https://example.com/optimizing-kmp-performance",
        imageUrl = "https://picsum.photos/seed/8/300/200",
        keywords = listOf("Performance", "KMP")
    ),

    Article(
        id = 9,
        author = "Ivy Martinez",
        publishedAt = "2025-01-05 12:15:00",
        title = "Advanced Logging Techniques with Kermit in KMP",
        url = "https://example.com/kermit-logging-kmp",
        imageUrl = "https://picsum.photos/seed/9/300/200",
        keywords = listOf("Logging", "Kermit")
    ),
    Article(
        id = 10,
        author = "Jack Anderson",
        publishedAt = "2025-01-05 12:15:00",
        title = "Building Accessible UIs with Compose Multiplatform",
        url = "https://example.com/accessible-ui-compose",
        imageUrl = "https://picsum.photos/seed/10/300/200",
        keywords = listOf("Accessibility", "Compose")
    ),
    Article(
        id = 11,
        author = "Karen Phillips",
        publishedAt = "2025-01-05 12:15:00",
        title = "Best Practices for Error Handling in KMP Applications",
        url = "https://example.com/error-handling-kmp",
        imageUrl = "https://picsum.photos/seed/11/300/200",
        keywords = listOf("Errors", "Best Practices")
    )
)

class ArticleViewModel: ViewModel() {

    private val _articles = MutableStateFlow(getArticles())
    val articles = _articles.asStateFlow()

    private fun getArticles() = fakeArticles

}