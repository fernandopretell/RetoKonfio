package com.fernandopretell.retokonfio.domain.usecase

import com.fernandopretell.retokonfio.domain.model.Dog
import com.fernandopretell.retokonfio.domain.repository.DogRepository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val repository: DogRepository
) {
    suspend operator fun invoke(): List<Dog> = repository.getDogs()
}
