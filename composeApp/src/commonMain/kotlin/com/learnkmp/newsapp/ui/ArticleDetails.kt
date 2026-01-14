package com.learnkmp.newsapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.utils.formatDateTime
import kmpnewsapp.composeapp.generated.resources.Res
import kmpnewsapp.composeapp.generated.resources.open_url
import kmpnewsapp.composeapp.generated.resources.read_full_story
import org.jetbrains.compose.resources.stringResource


@Composable
fun ArticleDetails(
    article: Article,
    onBack: () -> Unit,
) {
    val scroll = rememberScrollState()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = {
                    Text(
                        article.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(backBtnImageVector(), contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(padding)
        ) {

            NewsAsyncImage(
                url = article.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(0.dp))
                    .padding(bottom = 0.dp),
            )

            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = article.title,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
                )

                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "${article.source} • ${formatDateTime(article.pubDate)}",
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF6B7280))
                )

                if (article.keywords.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        article.keywords.take(5).forEach { kw ->
                            KeywordChip(text = kw)
                        }
                    }
                }

                if (!article.description.isNullOrBlank()) {
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = article.description.orEmpty(),
                        style = TextStyle(fontSize = 15.sp)
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 2.dp
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                        Text(
                            text = stringResource(Res.string.read_full_story),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        OutlinedButton(onClick = { uriHandler.openUri(article.link) }) {
                            Text(stringResource(Res.string.open_url, article.link))
//                            Text("Open ${article.link}")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ArticleDetailsPreview() {

    ArticleDetails(
        Article(
            source = "Alice Johnson",
            pubDate = "2025-01-05 12:15:00",
            title = "The Rise of Kotlin Multiplatform in Mobile Development",
            link = "https://example.com/kotlin-multiplatform",
            imageUrl = "https://picsum.photos/seed/1/300/200",
            keywords = listOf("KMP", "Mobile", "Trends")
        )
    ) {

    }
}




