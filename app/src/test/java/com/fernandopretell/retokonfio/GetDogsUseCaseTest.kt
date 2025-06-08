package com.fernandopretell.retokonfio

import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.domain.cache.CachePolicyManager
import com.fernandopretell.retokonfio.domain.usecase.GetDogsUseCase
import com.fernandopretell.retokonfio.fake.FakeDogRepository
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetDogsUseCaseTest {

    private lateinit var fakeRepository: FakeDogRepository
    private lateinit var cachePolicy: CachePolicyManager
    private lateinit var useCase: GetDogsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeDogRepository()
        cachePolicy = mock()
        useCase = GetDogsUseCase(fakeRepository, cachePolicy)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `returns dogs from repository when forceRefresh is false`() = runTest {
        fakeRepository.setDogs(
            listOf(
                DogEntity("1", "Chief", "Guard", 4, "url"),
                DogEntity("2", "King", "Alpha", 5, "url")
            )
        )

        whenever(cachePolicy.shouldForceRefresh(false)).thenReturn(false)

        val result = useCase(userRequestedRefresh = false)

        assertEquals(2, result.size)
        assertEquals("Chief", result[0].name)
    }

    @Test
    fun `returns updated dogs when forceRefresh is true`() = runTest {
        fakeRepository.setDogs(
            listOf(DogEntity("1", "Chief", "Guard", 4, "url"))
        )

        whenever(cachePolicy.shouldForceRefresh(true)).thenReturn(true)

        val result = useCase(userRequestedRefresh = true)

        verify(cachePolicy).updateTimestamp()
        assertEquals("Chief", result[0].name)
    }
}
