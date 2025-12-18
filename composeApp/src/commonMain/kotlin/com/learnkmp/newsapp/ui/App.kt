package com.learnkmp.newsapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.learnkmp.newsapp.models.Article


data object NewsListKey : NavKey
data class NewsDetailsKey(val article: Article) : NavKey

@Composable
fun App() {
    MaterialTheme {
        val backStack = remember { mutableStateListOf<NavKey>(NewsListKey) }

        NavDisplay(
            modifier = Modifier
                .fillMaxSize(),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<NewsListKey> {
                    FeedList(
                        onArticleClick = { article ->
                            backStack.add(NewsDetailsKey(article))

                        }
                    )
                }
                entry<NewsDetailsKey> { key ->
                    ArticleDetails(article = key.article) {
                        backStack.removeLastOrNull()
                    }
                }
            }
        )
    }
}



