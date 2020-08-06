package com.nikasov.winstarstest.ui.fragment.dayoff

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.DayOffAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_day_off.*
import java.util.*

@AndroidEntryPoint
class DayOffFragment : Fragment(R.layout.fragment_day_off) {

    private val viewModel : DayOffViewModel by viewModels()
    private lateinit var datePicker : MaterialDatePicker<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        setUpUI()
    }

    private fun setUpUI() {
        setUpCalendar()
        setUpList()
        calendarBtn.setOnClickListener {
            showCalendar()
        }
    }

    private fun setUpList() {
        val messageAdapter = DayOffAdapter()
        messageAdapter.submitList(viewModel.getDayOffList())
        dayOffRecycler.apply {
            adapter = messageAdapter
        }
    }

    private fun setUpCalendar() {
        val builder = MaterialDatePicker.Builder.datePicker()
            .setSelection(Calendar.getInstance().timeInMillis)
        datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener {
            calendarBtn.isEnabled = true
        }
        datePicker.addOnDismissListener {
            calendarBtn.isEnabled = true
        }
    }

    private fun showCalendar() {
        calendarBtn.isEnabled = false
        datePicker.show(childFragmentManager, datePicker.toString())
    }
}