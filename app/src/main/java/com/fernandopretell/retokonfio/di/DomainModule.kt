package com.fernandopretell.retokonfio.di

import com.fernandopretell.retokonfio.domain.repository.DogRepository
import com.fernandopretell.retokonfio.domain.usecase.GetDogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetDogsUseCase(repository: DogRepository): GetDogsUseCase =
        GetDogsUseCase(repository)
}
