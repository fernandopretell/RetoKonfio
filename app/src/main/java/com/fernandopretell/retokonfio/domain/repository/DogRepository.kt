package com.fernandopretell.retokonfio.domain.repository

import com.fernandopretell.retokonfio.domain.model.Dog

interface DogRepository {
    suspend fun getDogs(): List<Dog>
}
