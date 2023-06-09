package ru.rainman.netology.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.data.local.AppDb
import ru.netology.data.local.FlightsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideFlightsDao(db: AppDb) : FlightsDao = db.flightsDao

}