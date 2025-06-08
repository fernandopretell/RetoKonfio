package com.fernandopretell.retokonfio.di

import com.fernandopretell.retokonfio.data.repository.DogRepositoryImpl
import com.fernandopretell.retokonfio.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDogRepository(
        impl: DogRepositoryImpl
    ): DogRepository
}
