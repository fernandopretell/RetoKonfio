package com.fernandopretell.retokonfio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernandopretell.retokonfio.data.local.entity.DogEntity

@Database(entities = [DogEntity::class], version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao
}
