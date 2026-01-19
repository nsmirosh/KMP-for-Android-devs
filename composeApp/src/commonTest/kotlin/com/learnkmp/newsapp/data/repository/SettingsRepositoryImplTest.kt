package com.learnkmp.newsapp.data.repository

import com.learnkmp.newsapp.domain.FakeDataStore
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SettingsRepositoryImplTest {

    private lateinit var repository: SettingsRepository

    @BeforeTest
    fun setup() {
        val fakeDataStore = FakeDataStore()
        repository = SettingsRepositoryImpl(fakeDataStore)
    }

    @Test
    fun getSelectedCategory_returnsNullInitially() = runTest {
        val result = repository.getSelectedCategory()
        assertTrue(result is Result.Success)
        assertNull(result.data)
    }

    @Test
    fun saveSelectedCategory_savesCategory() = runTest {
        repository.saveSelectedCategory(Category.BUSINESS)
        val result = repository.getSelectedCategory()
        assertTrue(result is Result.Success)
        assertEquals(Category.BUSINESS, result.data)
    }

    @Test
    fun saveSelectedCategory_nullClearsCategory() = runTest {
        repository.saveSelectedCategory(Category.BUSINESS)
        repository.saveSelectedCategory(null)
        val result = repository.getSelectedCategory()
        assertTrue(result is Result.Success)
        assertNull(result.data)
    }
}
