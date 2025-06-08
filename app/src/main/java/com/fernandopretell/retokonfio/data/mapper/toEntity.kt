package com.fernandopretell.retokonfio.data.mapper

import com.fernandopretell.retokonfio.data.local.entity.DogEntity
import com.fernandopretell.retokonfio.data.model.DogDto

fun DogDto.toEntity() = DogEntity(id, name, breed, age, imageUrl)


