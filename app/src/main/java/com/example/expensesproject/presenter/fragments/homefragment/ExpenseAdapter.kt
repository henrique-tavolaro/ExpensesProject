package com.example.expensesproject.presenter.fragments.homefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensesproject.databinding.AdapterExpenseRvBinding
import com.example.expensesproject.domain.model.Expense
import com.example.expensesproject.presenter.fragments.homefragment.ExpenseAdapter.ViewHolder

class ExpenseAdapter : ListAdapter<Expense, ViewHolder>(ExpenseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder private constructor(
        val binding: AdapterExpenseRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Expense
        ) {
            binding.expense = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                val binding = AdapterExpenseRvBinding
                    .inflate(view, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ExpenseDiffCallback : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem == newItem
    }
}