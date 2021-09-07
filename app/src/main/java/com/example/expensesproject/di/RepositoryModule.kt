package com.example.expensesproject.di

import com.example.expensesproject.datasource.room.MyDao
import com.example.expensesproject.domain.repositories.MyRepository
import com.example.expensesproject.domain.repositories.MyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        dao: MyDao
    ) : MyRepository {
        return MyRepositoryImpl(dao)
    }
}