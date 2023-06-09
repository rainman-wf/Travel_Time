package ru.rainman.netology.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.data.impl.FlightsRepositoryImpl
import ru.netology.domain.repository.FlightsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindFlightsRepository(impl: FlightsRepositoryImpl) : FlightsRepository

}