package com.learnkmp.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles WHERE category = :category")
    suspend fun getArticlesByCategory(category: String?): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE category IS NULL")
    suspend fun getArticlesWithoutCategory(): List<ArticleEntity>

    @Query("DELETE FROM articles WHERE category = :category")
    suspend fun deleteArticlesByCategory(category: String?)

    @Query("DELETE FROM articles WHERE category IS NULL")
    suspend fun deleteArticlesWithoutCategory()
}
