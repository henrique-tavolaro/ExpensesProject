package com.example.expensesproject.domain.repositories

import com.example.expensesproject.datasource.room.MyDao
import com.example.expensesproject.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val dao: MyDao
) : MyRepository {

    override suspend fun addExpense(expense: Expense) {
        dao.addExpense(expense)
    }

    override suspend fun getAllExpenses() : Flow<List<Expense>> {
        return dao.getAllExpenses()
    }

    override suspend fun deleteExpense(expense: Expense) {
        dao.deleteExpense(expense)
    }
}