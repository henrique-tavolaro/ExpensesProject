package com.example.expensesproject.datasource

import com.example.expensesproject.datasource.room.MyDao
import com.example.expensesproject.domain.model.Category
import com.example.expensesproject.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

val expense1 = Expense(1, "rent", Category.HOME, 1000.0, Date())
val expense2 = Expense(2, "food", Category.FOOD, 20.0, Date())

class FakeDao(
    val expenseList: MutableList<Expense> = mutableListOf()
): MyDao {

    override suspend fun addExpense(expense: Expense) {
        expenseList.add(expense)
    }

    override fun getAllExpenses(): Flow<List<Expense>> = flow {
        emit(expenseList)
    }

    override fun deleteExpense(expense: Expense) {
        expenseList.remove(expense)
    }


}