package com.fernandopretell.retokonfio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "dogs")
data class DogEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val breed: String,
    val age: Int,
    val imageUrl: String
)
