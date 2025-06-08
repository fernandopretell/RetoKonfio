package com.fernandopretell.retokonfio

import com.fernandopretell.retokonfio.data.local.DogDao
import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.data.mapper.toEntity
import com.fernandopretell.retokonfio.data.model.DogDto
import com.fernandopretell.retokonfio.data.remote.DogApiService
import com.fernandopretell.retokonfio.data.repository.DogRepositoryImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class DogRepositoryImplTest {

    private val api = mock<DogApiService>()
    private val dao = mock<DogDao>()

    private lateinit var repository: DogRepositoryImpl

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = DogRepositoryImpl(api, dao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `returns local data when available`() = runTest {
        val localDogs = listOf(DogEntity("1", "Chief", "Bodyguard", 4, "url"))
        whenever(dao.getDogs()).thenReturn(localDogs)

        val result = repository.getDogs(forceRefresh = false)

        assertEquals(localDogs, result)
        verify(api, never()).getDogs()
    }

    @Test
    fun `fetches from remote when local data is empty`() = runTest {
        whenever(dao.getDogs()).thenReturn(emptyList())

        val remoteDogs = listOf(DogDto("1", "Chief", "Bodyguard", 4, "url"))
        whenever(api.getDogs()).thenReturn(remoteDogs)

        val expectedEntities = remoteDogs.map { it.toEntity() }

        val result = repository.getDogs(forceRefresh = false)

        assertEquals(expectedEntities, result)
        verify(dao).insertDogs(expectedEntities)
        verify(api).getDogs()
    }

    @Test
    fun `returns local data if remote call fails`() = runTest {
        whenever(dao.getDogs()).thenReturn(emptyList())

        whenever(api.getDogs()).thenThrow(RuntimeException("Network error"))

        assertFailsWith<RuntimeException> {
            repository.getDogs(forceRefresh = true)
        }

        verify(dao, times(1)).getDogs()
        verify(api, times(1)).getDogs()
    }

    @Test
    fun `returns local data on fallback if available`() = runTest {
        val localDogs = listOf(DogEntity("1", "Chief", "Bodyguard", 4, "url"))
        whenever(dao.getDogs()).thenReturn(localDogs)

        whenever(api.getDogs()).thenThrow(RuntimeException("Network error"))

        val result = repository.getDogs(forceRefresh = true)

        assertEquals(localDogs, result)
        verify(api).getDogs()
    }
}
