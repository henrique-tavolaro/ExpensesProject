package com.example.expensesproject.domain.repositories

import com.example.expensesproject.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface MyRepository {

    suspend fun addExpense(expense: Expense)

    suspend fun getAllExpenses() : Flow<List<Expense>>

    suspend fun deleteExpense(expense: Expense)

}