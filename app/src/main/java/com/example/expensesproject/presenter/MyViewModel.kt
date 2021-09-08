package com.example.expensesproject.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesproject.utils.Event
import com.example.expensesproject.domain.model.Expense
import com.example.expensesproject.domain.repositories.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {

    private val _allExpenses : MutableLiveData<Event<List<Expense>>> = MutableLiveData()
    val allExpenses : LiveData<Event<List<Expense>>> = _allExpenses

    init {
        getAllExpenses()
    }

    fun getAllExpenses(){
        viewModelScope.launch {
            repository.getAllExpenses().collect {
                _allExpenses.postValue(Event(it))
            }
        }
    }

    fun addExpense(expense: Expense){
        viewModelScope.launch {
            repository.addExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense){
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }


}