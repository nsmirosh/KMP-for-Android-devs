package com.learnkmp.newsapp.data.repository

import com.learnkmp.newsapp.data.database.ArticleDao
import com.learnkmp.newsapp.data.database.ArticleEntity

class FakeArticleDao : ArticleDao {
    private val articles = mutableListOf<ArticleEntity>()

    override suspend fun insertArticles(articles: List<ArticleEntity>) {
        this.articles.addAll(articles)
    }

    override suspend fun getArticlesByCategory(category: String?): List<ArticleEntity> {
        return articles.filter { it.category == category }
    }

    override suspend fun getArticlesWithoutCategory(): List<ArticleEntity> {
        return articles.filter { it.category == null }
    }

    override suspend fun deleteArticlesByCategory(category: String?) {
        articles.removeAll { it.category == category }
    }

    override suspend fun deleteArticlesWithoutCategory() {
        articles.removeAll { it.category == null }
    }
}
