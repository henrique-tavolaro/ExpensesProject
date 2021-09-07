package com.example.expensesproject.datasource.room

import androidx.room.*
import com.example.expensesproject.domain.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpense(expense: Expense)

    @Transaction
    @Query("SELECT * FROM expense")
    fun getAllExpenses() : Flow<List<Expense>>

    @Delete
    fun deleteExpense(expense: Expense)

}