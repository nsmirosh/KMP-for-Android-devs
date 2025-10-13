package com.learnkmp.newsapp.networking

import com.learnkmp.newsapp.models.Article


interface NewsDataRepo {
    suspend fun getNewsData(): List<Article>
}

class NewsDataRepoImpl : NewsDataRepo {

    override suspend fun getNewsData(): List<Article> {
        //TODO fetching latest new from newsdata.io

    }
}