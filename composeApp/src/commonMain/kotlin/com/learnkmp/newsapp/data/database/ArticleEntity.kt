package com.learnkmp.newsapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learnkmp.newsapp.data.networking.ArticleDto
import com.learnkmp.newsapp.domain.model.Article

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val id: String,
    val source: String,
    val pubDate: String,
    val title: String,
    val link: String,
    val description: String?,
    val imageUrl: String?,
    val sourceIconUrl: String?,
    val keywords: String,
    val category: String?
)

fun Article.toEntity(category: String?): ArticleEntity {
    return ArticleEntity(
        id = id,
        source = source,
        pubDate = pubDate,
        title = title,
        link = link,
        description = description,
        imageUrl = imageUrl,
        sourceIconUrl = sourceIconUrl,
        keywords = keywords.joinToString(","),
        category = category
    )
}

fun ArticleDto.toEntity(category: String?): ArticleEntity {
    return ArticleEntity(
        id = id,
        source = source,
        pubDate = pubDate,
        title = title,
        link = link,
        description = description,
        imageUrl = imageUrl,
        sourceIconUrl = sourceIconUrl,
        keywords = keywords.joinToString(","),
        category = category
    )
}

fun ArticleDto.toDomain(): Article {
    return Article(
        id = id,
        source = source,
        pubDate = pubDate,
        title = title,
        link = link,
        description = description,
        imageUrl = imageUrl,
        sourceIconUrl = sourceIconUrl,
        keywords = keywords
    )
}

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        source = source,
        pubDate = pubDate,
        title = title,
        link = link,
        description = description,
        imageUrl = imageUrl,
        sourceIconUrl = sourceIconUrl,
        keywords = if (keywords.isEmpty()) emptyList() else keywords.split(",")
    )
}
