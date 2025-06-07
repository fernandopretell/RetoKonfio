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

    private var isFirstLaunch = true

    override suspend fun getDogs(): List<Dog> {
        return if (isFirstLaunch) {
            val remote = api.getDogs()
            dao.insertDogs(remote.map { it.toEntity() })
            isFirstLaunch = false
            remote
        } else {
            dao.getDogs().map { it.toDomain() }
        }
    }
}
