package com.learnkmp.newsapp.data.repository

import com.learnkmp.newsapp.domain.FakeDataStore
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SettingsRepositoryImplTest{

    private lateinit var repository: SettingsRepository

    @BeforeTest
    fun setup() {
        val fakeDataStore = FakeDataStore()
        repository = SettingsRepositoryImpl(fakeDataStore)
    }


    @Test
    fun getSelectedCategory_returnsNullInitially() = runTest {
        val category = repository.getSelectedCategory().first()
        assertNull(category)
    }

    @Test
    fun saveSelectedCategory_savesCategory() = runTest {
        repository.saveSelectedCategory(Category.BUSINESS)
        val category = repository.getSelectedCategory().first()
        assertEquals(Category.BUSINESS, category)
    }

    @Test
    fun saveSelectedCategory_nullClearsCategory() = runTest {
        repository.saveSelectedCategory(Category.BUSINESS)
        repository.saveSelectedCategory(null)
        val category = repository.getSelectedCategory().first()
        assertNull(category)
    }
}
