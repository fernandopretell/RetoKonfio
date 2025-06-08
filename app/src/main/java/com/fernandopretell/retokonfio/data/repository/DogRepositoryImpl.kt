package com.fernandopretell.retokonfio.data.repository

import com.fernandopretell.retokonfio.data.local.DogDao
import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.data.mapper.toEntity
import com.fernandopretell.retokonfio.data.remote.DogApiService
import com.fernandopretell.retokonfio.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val api: DogApiService,
    private val dao: DogDao
) : DogRepository {

    override suspend fun getDogs(forceRefresh: Boolean): List<DogEntity> {
        val localDogs = dao.getDogs()

        return if (forceRefresh || localDogs.isEmpty()) {
            try {
                val remoteDogs = api.getDogs()
                val entities = remoteDogs.map { it.toEntity() }

                dao.clearDogs()
                dao.insertDogs(entities)

                entities
            } catch (e: Exception) {
                if (localDogs.isNotEmpty()) {
                    localDogs
                } else {
                    throw e
                }
            }
        } else {
            localDogs
        }
    }
}

