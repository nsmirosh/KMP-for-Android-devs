package com.learnkmp.newsapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.learnkmp.newsapp.models.Article
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val backStack = remember { mutableStateListOf<Any>(Destination.NewsListKey) }

        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Destination.NewsListKey> {
                    FeedList(
                        onArticleClick = { article ->
                            backStack.add(Destination.NewsDetailsKey(article))

                        }
                    )
                }
                entry<Destination.NewsDetailsKey> { key ->
                    ArticleDetails(article = key.article) {
                        backStack.removeLastOrNull()
                    }
                }
            }
        )
    }
}


sealed class Destination {
    data object NewsListKey : Destination()
    data class NewsDetailsKey(val article: Article) : Destination()
}


@Composable
fun FeedList(
    viewModel: ArticleViewModel = viewModel { ArticleViewModel() },
    modifier: Modifier = Modifier,
    onArticleClick: (Article) -> Unit = {}
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
            ArticleListItem(article = article, onClick = { onArticleClick(article) })
        }
    }
}