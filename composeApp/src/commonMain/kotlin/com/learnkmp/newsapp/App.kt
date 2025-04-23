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
import com.learnkmp.newsapp.utils.formatDateTime
import com.learnkmp.newsapp.utils.generateClickId
import com.learnkmp.newsapp.utils.getPlatformName
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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
    buildKtor()
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
        println("server string = ${article.id}-${getPlatformName()}-${generateClickId()}")
    }) {

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
                    text = formatDateTime(article.publishedAt),
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

private fun buildKtor() {
    val client = HttpClient()
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    coroutineScope.launch {
        val response: HttpResponse = client.get("https://ktor.io/")
        println("response.status = ${response.status}")
    }
}


val fakeArticles = listOf(
    Article(
        id = 1,
        author = "Alice Johnson",
        publishedAt = "2025-01-05 12:15:00",
        title = "The Rise of Kotlin Multiplatform in Mobile Development",
        url = "https://example.com/kotlin-multiplatform"
    ),
    Article(
        id = 2,
        author = "Bob Smith",
        publishedAt = "2025-01-05 13:15:00",
        title = "Exploring Clean Architecture in Android",
        url = "https://example.com/clean-architecture-android"
    ),
    Article(
        id = 3,
        author = "Charlotte Brown",
        publishedAt = "2025-01-05 14:15:00",
        title = "How to Effectively Test Asynchronous Code in Android",
        url = "https://example.com/testing-async-android"
    ),
    Article(
        id = 4,
        author = "David Lee",
        publishedAt = "2025-01-05 15:15:00",
        title = "Top 5 Jetpack Compose Libraries to Know in 2025",
        url = "https://example.com/jetpack-compose-libraries"
    ),
    Article(
        id = 5,
        author = "Emily Davis",
        publishedAt = "2025-01-05 16:15:00",
        title = "Understanding Dependency Injection with Koin",
        url = "https://example.com/dependency-injection-koin"
    ),
    Article(
        id = 6,
        author = "Frank Wilson",
        publishedAt = "2025-01-10 11:45:00",
        title = "Mastering Coroutine Flows for Reactive Android Apps",
        url = "https://example.com/coroutine-flows"
    ),
    Article(
        id = 7,
        author = "Grace Thomas",
        publishedAt = "2025-01-05 12:15:00",
        title = "Introduction to Voyager for State Management in KMP",
        url = "https://example.com/voyager-state-management"
    ),
    Article(
        id = 8,
        author = "Henry Moore",
        publishedAt = "2025-01-05 10:15:00",
        title = "Optimizing Performance in KMP Projects",
        url = "https://example.com/optimizing-kmp-performance"
    ),

    Article(
        id = 9,
        author = "Ivy Martinez",
        publishedAt = "2025-01-05 12:15:00",
        title = "Advanced Logging Techniques with Kermit in KMP",
        url = "https://example.com/kermit-logging-kmp"
    ),
    Article(
        id = 10,
        author = "Jack Anderson",
        publishedAt = "2025-01-05 12:15:00",
        title = "Building Accessible UIs with Compose Multiplatform",
        url = "https://example.com/accessible-ui-compose"
    ),
    Article(
        id = 11,
        author = "Karen Phillips",
        publishedAt = "2025-01-05 12:15:00",
        title = "Best Practices for Error Handling in KMP Applications",
        url = "https://example.com/error-handling-kmp"
    )
)
