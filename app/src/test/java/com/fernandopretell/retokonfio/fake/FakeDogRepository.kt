package com.fernandopretell.retokonfio.fake

import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.domain.repository.DogRepository

class FakeDogRepository : DogRepository {

    private var dogs = emptyList<DogEntity>()

    fun setDogs(newDogs: List<DogEntity>) {
        dogs = newDogs
    }

    override suspend fun getDogs(forceRefresh: Boolean): List<DogEntity> {
        return dogs
    }
}