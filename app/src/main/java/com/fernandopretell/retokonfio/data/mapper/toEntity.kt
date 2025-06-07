package com.fernandopretell.retokonfio.data.mapper

import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.domain.model.Dog

fun Dog.toEntity() = DogEntity(
    id = id,
    name = name,
    breed = breed,
    age = age,
    imageUrl = imageUrl
)

fun DogEntity.toDomain() = Dog(
    id = id,
    name = name,
    breed = breed,
    age = age,
    imageUrl = imageUrl
)
