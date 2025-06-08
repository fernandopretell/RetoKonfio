package com.fernandopretell.retokonfio.data.repository

import com.fernandopretell.retokonfio.data.local.DogDao
import com.fernandopretell.retokonfio.data.mapper.toDomain
import com.fernandopretell.retokonfio.data.mapper.toEntity
import com.fernandopretell.retokonfio.data.remote.DogApiService
import com.fernandopretell.retokonfio.domain.model.Dog
import com.fernandopretell.retokonfio.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val api: DogApiService,
    private val dao: DogDao
) : DogRepository {

    override suspend fun getDogs(): List<Dog> {
        val localDogs = dao.getDogs()
        return if (localDogs.isEmpty()) {
            val remote = api.getDogs()
            dao.insertDogs(remote.map { it.toEntity() })
            remote
        } else {
            localDogs.map { it.toDomain() }
        }
    }
}
