package com.fernandopretell.retokonfio.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DogDto(
    val id: String = UUID.randomUUID().toString(),

    @SerialName("dogName")
    val name: String,

    @SerialName("description")
    val breed: String,

    val age: Int,

    @SerialName("image")
    val imageUrl: String
)
