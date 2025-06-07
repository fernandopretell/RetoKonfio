package com.fernandopretell.retokonfio.di

import android.content.Context
import androidx.room.Room
import com.fernandopretell.retokonfio.data.local.DogDao
import com.fernandopretell.retokonfio.data.local.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DogDatabase =
        Room.databaseBuilder(context, DogDatabase::class.java, "dog_db")
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    fun provideDogDao(db: DogDatabase): DogDao = db.dogDao()
}
