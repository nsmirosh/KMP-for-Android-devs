package com.learnkmp.newsapp.domain.usecase

import com.learnkmp.newsapp.domain.model.Article
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.NewsRepository

class GetArticlesUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(category: Category?): Result<List<Article>> {
        return repository.getNews(category)
    }
}
