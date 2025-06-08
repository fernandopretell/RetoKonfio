package com.fernandopretell.retokonfio.presentation.mapper

import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.presentation.model.DogUi

fun DogEntity.toUi(): DogUi = DogUi(
    id = id,
    name = name,
    breed = breed,
    ageFormatted = "Almost ${age + 1} years",
    imageUrl = imageUrl
)
