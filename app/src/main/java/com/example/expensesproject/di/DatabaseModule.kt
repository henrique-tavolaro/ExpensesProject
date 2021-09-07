package com.example.expensesproject.di

import android.content.Context
import androidx.room.Room
import com.example.expensesproject.datasource.room.MyDatabase
import com.example.expensesproject.datasource.room.MyDatabase.Companion.DATABASE
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
    fun provideUserDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MyDatabase::class.java,
        DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideOrderDao(db: MyDatabase) = db.getDao()

}
