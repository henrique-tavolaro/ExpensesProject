package com.example.expensesproject.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensesproject.domain.model.Expense
import com.example.expensesproject.domain.model.Converters

@Database(entities = [
            Expense::class
    ], version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getDao(): MyDao

    companion object{
        const val DATABASE: String = "my_db"
    }
}

