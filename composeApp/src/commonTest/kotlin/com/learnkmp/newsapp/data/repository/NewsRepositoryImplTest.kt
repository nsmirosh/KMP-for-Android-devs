package com.learnkmp.newsapp.data.repository

import com.learnkmp.newsapp.data.database.ArticleDao
import com.learnkmp.newsapp.data.networking.ArticleDto
import com.learnkmp.newsapp.data.networking.ArticlesResponseDto
import com.learnkmp.newsapp.di.HttpClientQualifier
import com.learnkmp.newsapp.di.commonNetworkModule
import com.learnkmp.newsapp.di.repositoryModule
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.NewsRepository
import com.learnkmp.newsapp.utils.respondJson
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respondError
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewsRepositoryImplTest : KoinTest {

    private val repository: NewsRepository by inject()

    private var mockHandler: (suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData)? = null

    private fun testModule() = module {
        single<ArticleDao> { FakeArticleDao() }
        single<HttpClient>(named(HttpClientQualifier.PLATFORM)) {
            HttpClient(MockEngine { request ->
                mockHandler!!(request)
            })
        }
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule(), commonNetworkModule(), repositoryModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
    val articleDto = ArticleDto(
        id = "1",
        source = "Source",
        pubDate = "2024-01-24",
        title = "Title",
        link = "link",
        description = "description",
        imageUrl = "image",
        sourceIconUrl = "icon",
        keywords = listOf("keyword")
    )

    val successfulResponseDto  = ArticlesResponseDto(
        status = "success",
        totalResults = 1,
        results = listOf(articleDto)
    )


    @Test
    fun `getNews returns success when network call is successful`() = runTest {
        mockHandler = {
            respondJson(successfulResponseDto)
        }

        //we don't really care what category is being sent - since we're faking the return values
        val result = repository.getNews(null)

        assertTrue(result is Result.Success)
        assertEquals(1, result.data.size)
        assertEquals("Title", result.data[0].title)
    }

    @Test
    fun `getNews returns cached data when network call fails`() = runTest {

        var shouldFail = false
        mockHandler = {
            if (shouldFail) {
                respondError(HttpStatusCode.InternalServerError)
            } else {
                respondJson(successfulResponseDto)
            }
        }

        // Seed cache
        repository.getNews(null)

        // Now fail
        shouldFail = true
        val result = repository.getNews(null)

        assertTrue(result is Result.Success)
        assertEquals(1, result.data.size)
        assertEquals("Title", result.data[0].title)
    }

    @Test
    fun `getNews returns error when network call fails and no cache available`() = runTest {
        mockHandler = {
            respondError(HttpStatusCode.InternalServerError)
        }

        val result = repository.getNews(null)

        assertTrue(result is Result.Error)
    }

    @Test
    fun `getNews with category calls network with category parameter`() = runTest {
        val category = Category.BUSINESS
        val articleDto = ArticleDto(
            id = "1",
            source = "Source",
            pubDate = "2024-01-24",
            title = "Title",
            link = "link",
            keywords = emptyList()
        )
        val responseDto = ArticlesResponseDto(
            status = "success",
            totalResults = 1,
            results = listOf(articleDto)
        )

        var capturedCategory: String? = null
        mockHandler = { request ->
            capturedCategory = request.url.parameters["category"]
            respondJson(responseDto)
        }

        repository.getNews(category)

        assertEquals(category.value, capturedCategory)
    }
}
