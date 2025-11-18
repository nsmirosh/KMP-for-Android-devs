package com.learnkmp.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.utils.formatDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        FeedList(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp))
    }
}

@Composable
fun FeedList(
    viewModel: ArticleViewModel = viewModel { ArticleViewModel() },
    modifier: Modifier = Modifier,
) {
    val articles by viewModel.articles.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Top News",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
        }
        items(articles) { article ->
            ArticleItem(article = article)
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val randomIndex = (0 until pastelColors.size).random()
            val tint = pastelColors[randomIndex]
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(tint),
                error = ColorPainter(Color.Black),
                onError = {
                    println(it.result.throwable.printStackTrace())
                }
            )

            Spacer(modifier = Modifier.size(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = article.title,
                    style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )


                if (article.keywords.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        article.keywords.take(2).forEach { kw ->
                            KeywordChip(text = kw)
                        }
                    }
                }

                Spacer(modifier = Modifier.size(6.dp))

                val source = article.source.ifBlank { extractHost(article.link) }
                Text(
                    text = "$source • ${formatDateTime(article.pubDate)}",
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF6B7280))
                )
            }
        }
    }
}

@Composable
private fun KeywordChip(text: String) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFFF3F4F6), shape = RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(fontSize = 11.sp, color = Color(0xFF4B5563))
        )
    }
}

private fun extractHost(url: String): String {
    var u = url.trim()
    if (u.startsWith("https://")) u = u.removePrefix("https://")
    if (u.startsWith("http://")) u = u.removePrefix("http://")
    u = u.substringBefore('/')
    if (u.startsWith("www.")) u = u.removePrefix("www.")
    return u.ifBlank { "news" }
}

private val pastelColors = listOf(
    Color(0xFFE3F2FD), // light blue
    Color(0xFFFFF3E0), // light orange
    Color(0xFFE8F5E9), // light green
    Color(0xFFF3E5F5), // light purple
    Color(0xFFFFEBEE), // light red
    Color(0xFFE0F2F1), // teal
    Color(0xFFFFFDE7), // yellow
    Color(0xFFECEFF1)  // blue grey
)

