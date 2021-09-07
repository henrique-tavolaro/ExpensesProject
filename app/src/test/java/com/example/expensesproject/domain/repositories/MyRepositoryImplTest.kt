package com.example.expensesproject.domain.repositories

import com.example.expensesproject.datasource.FakeDao
import com.example.expensesproject.datasource.expense1
import com.example.expensesproject.datasource.expense2
import com.example.expensesproject.domain.model.Expense
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MyRepositoryImplTest{

    lateinit var repository: MyRepository

    lateinit var dao: FakeDao

    @Before
    fun setup(){
        dao = FakeDao()
        repository = MyRepositoryImpl(dao)
    }

    @Test
    fun shouldAddExpenseToDatabase() = runBlocking{
        assert(dao.expenseList.isEmpty())

        repository.addExpense(expense1)

        assert(dao.expenseList.isNotEmpty())
        assert(dao.expenseList.contains(expense1))
    }

    @Test
    fun shouldGetAllExpensesFromDatabase() = runBlocking {
        assert(dao.expenseList.isEmpty())

        repository.addExpense(expense1)
        repository.addExpense(expense2)

        val result = mutableListOf<Expense>()

        repository.getAllExpenses().collect {
            for(i in it){
                result.add(i)
            }
        }

        assert(result.isNotEmpty())
        assert(result.contains(expense1))
        assert(result.contains(expense2))
    }

    @Test
    fun shouldDeleteExpenseFromDatabase() = runBlocking {
        assert(dao.expenseList.isEmpty())

        repository.addExpense(expense1)

        assert(dao.expenseList.isNotEmpty())

        repository.delete(expense1)

        assert(dao.expenseList.isEmpty())
    }

}