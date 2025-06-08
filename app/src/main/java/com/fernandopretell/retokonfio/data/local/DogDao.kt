package com.fernandopretell.retokonfio.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernandopretell.retokonfio.data.local.entity.DogEntity

@Dao
interface DogDao {
    @Query("SELECT * FROM dogs")
    suspend fun getDogs(): List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogs(dogs: List<DogEntity>)

    @Query("DELETE FROM dogs")
    suspend fun clearDogs()

}
