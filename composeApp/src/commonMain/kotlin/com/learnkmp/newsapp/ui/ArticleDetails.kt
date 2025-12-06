package com.learnkmp.newsapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.utils.formatDateTime

@Composable
fun ArticleDetails(
    article: Article,
    onBack: () -> Unit,
) {
    val scroll = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(bottom = 24.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(onClick = onBack) {
                Text("Back")
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = /*extractHost(article.link)*/ "",
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            )
        }

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
                        text = "Read full story:",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    OutlinedButton(onClick = { uriHandler.openUri(article.link) }) {
                        Text("Open ${article.link}")
                    }
                }
            }
        }
    }
}
