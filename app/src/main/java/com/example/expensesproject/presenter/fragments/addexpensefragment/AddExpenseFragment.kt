package com.example.expensesproject.presenter.fragments.addexpensefragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.expensesproject.R
import com.example.expensesproject.databinding.FragmentAddExpenseBinding
import com.example.expensesproject.domain.model.Expense
import com.example.expensesproject.presenter.MyViewModel
import com.example.expensesproject.utils.MoneyTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*


@InternalCoroutinesApi
@AndroidEntryPoint
class AddExpenseFragment : Fragment() {

    val viewModel: MyViewModel by viewModels()

    lateinit var binding: FragmentAddExpenseBinding

    private var cal = Calendar.getInstance()
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_expense,
            container,
            false
        )

        val etName = binding.etName
        val dropdown = binding.dropdownMenu
        val categories = resources.getStringArray(R.array.category_array)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, categories)
        dropdown.setAdapter(arrayAdapter)

        val etValue = binding.etValue
        val mLocale = Locale("pt", "BR")
        etValue.addTextChangedListener(MoneyTextWatcher(etValue, mLocale))

        val tvDate = binding.tvDate
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        tvDate.text = sdf.format(Date())

        dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                tvDate.text = sdf.format(cal.time)
            }

        val datepicker = binding.dateIcon

        datepicker.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateSetListener, // This is the variable which have created globally and initialized in setupUI method.
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR), // Here the cal instance is created globally and used everywhere in the class where it is required.
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        val buttonAddExpense = binding.buttonAddExpense
        buttonAddExpense.setOnClickListener {
            val expense = Expense(
                id = 0,
                name = etName.text.toString(),
                category = dropdown.text.toString().uppercase(),
                value = etValue.text.toString(),
                date = sdf.parse(tvDate.text as String)
            )
            viewModel.addExpense(expense)
        }



        return binding.root
    }
}

