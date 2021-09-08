package com.example.expensesproject.presenter.fragments.homefragment

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.expensesproject.R
import com.example.expensesproject.domain.model.Category
import com.example.expensesproject.domain.model.Expense

@BindingAdapter("expenseName")
fun TextView.setExpenseName(expense: Expense?){
    expense?.let {
        text = it.name
    }
}

@BindingAdapter("expenseValue")
fun TextView.setExpenseValue(expense: Expense?){
    expense?.let {
        text = it.value
    }
}

@BindingAdapter("categoryIcon")
fun ImageView.setCategoryIcon(expense: Expense?){
    expense?.let {
        setImageResource(
            when (it.category) {
                Category.HOME.name -> R.drawable.ic_home_24
                Category.ENTERTAINMENT.name -> R.drawable.ic_entertainment_24
                Category.FOOD.name -> R.drawable.ic_food_24
                Category.OTHERS.name -> R.drawable.ic_other_24
                Category.HEALTH.name -> R.drawable.ic_health_24
                Category.EDUCATION.name -> R.drawable.ic_baseline_school_24
                else -> R.drawable.ic_add_24
            }
        )
    }
}