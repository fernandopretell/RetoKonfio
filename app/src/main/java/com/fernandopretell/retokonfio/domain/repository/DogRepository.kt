package com.fernandopretell.retokonfio.domain.repository

import com.fernandopretell.retokonfio.data.local.entity.DogEntity

interface DogRepository {
    suspend fun getDogs(forceRefresh: Boolean = false): List<DogEntity>
}
