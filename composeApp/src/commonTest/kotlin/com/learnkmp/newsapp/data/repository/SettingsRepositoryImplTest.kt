package com.learnkmp.newsapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.learnkmp.newsapp.di.repositoryModule
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SettingsRepositoryImplTest : KoinTest {

    private val repository: SettingsRepository by inject()

    private fun testModule() = module {
        single<DataStore<Preferences>> { FakeDataStore() }
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule() + repositoryModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getSelectedCategory returns null when no category is selected`() = runTest {
        val result = repository.getSelectedCategory()
        assertTrue(result is Result.Success)
        assertNull(result.data)
    }

    @Test
    fun `saveSelectedCategory saves the category`() = runTest {
        repository.saveSelectedCategory(Category.BUSINESS)
        val result = repository.getSelectedCategory()
        assertTrue(result is Result.Success)
        assertEquals(Category.BUSINESS, result.data)
    }

    @Test
    fun `saveSelectedCategory with null removes the category`() = runTest {
        repository.saveSelectedCategory(Category.BUSINESS)
        repository.saveSelectedCategory(null)
        val result = repository.getSelectedCategory()
        assertTrue(result is Result.Success)
        assertNull(result.data)
    }
}
