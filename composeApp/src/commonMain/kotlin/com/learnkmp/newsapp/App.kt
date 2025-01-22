package com.learnkmp.newsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.utils.getPlatformAndUniqueId
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        FeedList(fakeArticles, modifier = Modifier.fillMaxSize().padding(16.dp))
    }
}

@Composable
fun FeedList(
    articles: List<Article>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(articles, key = { article -> article.url }) { article ->
            ArticleItem(
                article = article,
            )
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.clickable {
        val identifier = getPlatformAndUniqueId()
        println("identifier = $identifier")
    }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = article.title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = article.publishedAt,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                )
                if (article.author.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = article.author,
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}

val fakeArticles = listOf(
    Article(
        author = "Alice Johnson",
        publishedAt = "2025-01-05 12:15:00",
        title = "The Rise of Kotlin Multiplatform in Mobile Development",
        url = "https://example.com/kotlin-multiplatform"
    ),
    Article(
        author = "Bob Smith",
        publishedAt = "2025-01-05 13:15:00",
        title = "Exploring Clean Architecture in Android",
        url = "https://example.com/clean-architecture-android"
    ),
    Article(
        author = "Charlotte Brown",
        publishedAt = "2025-01-05 14:15:00",
        title = "How to Effectively Test Asynchronous Code in Android",
        url = "https://example.com/testing-async-android"
    ),
    Article(
        author = "David Lee",
        publishedAt = "2025-01-05 15:15:00",
        title = "Top 5 Jetpack Compose Libraries to Know in 2025",
        url = "https://example.com/jetpack-compose-libraries"
    ),
    Article(
        author = "Emily Davis",
        publishedAt = "2025-01-05 16:15:00",
        title = "Understanding Dependency Injection with Koin",
        url = "https://example.com/dependency-injection-koin"
    ),
    Article(
        author = "Frank Wilson",
        publishedAt = "2025-01-10 11:45:00 ",
        title = "Mastering Coroutine Flows for Reactive Android Apps",
        url = "https://example.com/coroutine-flows"
    ),
    Article(
        author = "Grace Thomas",
        publishedAt = "2025-01-05 12:15:00",
        title = "Introduction to Voyager for State Management in KMP",
        url = "https://example.com/voyager-state-management"
    ),
    Article(
        author = "Henry Moore",
        publishedAt = "2025-01-05 10:15:00",
        title = "Optimizing Performance in KMP Projects",
        url = "https://example.com/optimizing-kmp-performance"
    ),

    Article(
        author = "Ivy Martinez",
        publishedAt = "2025-01-05 12:15:00",
        title = "Advanced Logging Techniques with Kermit in KMP",
        url = "https://example.com/kermit-logging-kmp"
    ),
    Article(
        author = "Jack Anderson",
        publishedAt = "2025-01-05 12:15:00",
        title = "Building Accessible UIs with Compose Multiplatform",
        url = "https://example.com/accessible-ui-compose"
    ),
    Article(
        author = "Karen Phillips",
        publishedAt = "2025-01-05 12:15:00",
        title = "Best Practices for Error Handling in KMP Applications",
        url = "https://example.com/error-handling-kmp"
    )
)
