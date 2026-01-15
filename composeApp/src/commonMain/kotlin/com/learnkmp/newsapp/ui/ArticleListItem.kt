package com.learnkmp.newsapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learnkmp.newsapp.domain.model.Article
import com.learnkmp.newsapp.utils.formatDateTime

@Composable
fun ArticleListItem(
    article: Article,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NewsAsyncImage(
                url = article.imageUrl,
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(10.dp))
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

                Text(
                    text = "${article.source} • ${formatDateTime(article.pubDate)}",
                    style = TextStyle(fontSize = 12.sp, color = Color(0xFF6B7280))
                )
            }
        }
    }
}
