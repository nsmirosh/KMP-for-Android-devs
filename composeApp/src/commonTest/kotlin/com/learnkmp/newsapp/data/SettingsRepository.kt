package com.learnkmp.newsapp.data

import com.learnkmp.newsapp.domain.model.Article
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.NewsRepository


class FakeNewsRepository : NewsRepository {
    override suspend fun getNews(category: Category?): Result<List<Article>> {
        TODO("Not yet implemented")
    }

}

