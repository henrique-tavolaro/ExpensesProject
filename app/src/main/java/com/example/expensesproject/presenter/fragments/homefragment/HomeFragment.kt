package com.example.expensesproject.presenter.fragments.homefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.expensesproject.R
import com.example.expensesproject.databinding.FragmentHomeBinding
import com.example.expensesproject.domain.model.Category
import com.example.expensesproject.presenter.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val adapter = ExpenseAdapter()
        binding.rvExpense.adapter = adapter

        viewModel.allExpenses.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let { list ->
                adapter.submitList(list)
                Log.d("TAG1", Category.HOME.name)
            }
        })

        val fab = binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddExpenseFragment()
            )
        }





        return binding.root
    }

}