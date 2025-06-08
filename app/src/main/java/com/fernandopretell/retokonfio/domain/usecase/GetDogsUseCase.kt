package com.fernandopretell.retokonfio.domain.usecase

import com.fernandopretell.retokonfio.domain.cache.CachePolicy
import com.fernandopretell.retokonfio.domain.repository.DogRepository
import com.fernandopretell.retokonfio.presentation.mapper.toUi
import com.fernandopretell.retokonfio.presentation.model.DogUi
import javax.inject.Inject

open class GetDogsUseCase @Inject constructor(
    private val repository: DogRepository,
    private val cachePolicy: CachePolicy
) {
    open suspend operator fun invoke(userRequestedRefresh: Boolean = false): List<DogUi> {
        val forceRefresh = cachePolicy.shouldForceRefresh(userRequestedRefresh)
        val dogs = repository.getDogs(forceRefresh).map { it.toUi() }

        if (forceRefresh) {
            cachePolicy.updateTimestamp()
        }

        return dogs
    }
}


