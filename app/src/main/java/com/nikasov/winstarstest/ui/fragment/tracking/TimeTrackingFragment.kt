package com.nikasov.winstarstest.ui.fragment.tracking

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.google.android.material.datepicker.MaterialDatePicker
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.Utils
import com.nikasov.winstarstest.ui.adapter.TimeTrackingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_number_picker.*
import kotlinx.android.synthetic.main.fragment_time_tracking.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TimeTrackingFragment : Fragment(R.layout.fragment_time_tracking) {
//todo: починить спинеры, добавить по установке времени добавление, узнать как енум в руме
    private val viewModel : TimeTrackingViewModel by viewModels()
    private lateinit var datePicker : MaterialDatePicker<*>
    private var today : Long = MaterialDatePicker.todayInUtcMilliseconds()

    private lateinit var numberPickerDialog : MaterialDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        setUpCalendar()
        setUpList()
    }

    private fun setUpList() {
        val timeTrackingAdapter = TimeTrackingAdapter()
        tracksRecycler.apply {
            adapter = timeTrackingAdapter
        }
        viewModel.trackingList.observe(viewLifecycleOwner, Observer { list ->
            timeTrackingAdapter.submitList(list)
        })
    }

    private fun initUi() {
        setUpNumberPicker()
        viewModel.update(today)
        viewModel.currentDate.observe(viewLifecycleOwner, Observer { date ->
            dayText.text = Utils.formatDate(date, resources.getString(R.string.day_format))
            yearText.text = Utils.formatDate(date, resources.getString(R.string.year_format))
        })
        calendarBtn.setOnClickListener {
            showCalendar()
        }
        timeBtn.setOnClickListener {
            showNumberPicker()
        }
        finishWorkBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setUpNumberPicker() {
        numberPickerDialog = MaterialDialog(requireContext())
            .noAutoDismiss()
            .customView(R.layout.dialog_number_picker)
        numberPickerDialog.numberPicker.minValue = 1
        numberPickerDialog.numberPicker.maxValue = 24
        numberPickerDialog.ok.setOnClickListener {
            viewModel.addTimeTracking(trackText.text.toString(), numberPickerDialog.numberPicker.value)
            numberPickerDialog.dismiss()
        }
        numberPickerDialog.cancel.setOnClickListener {
            numberPickerDialog.dismiss()
        }
    }

    private fun showNumberPicker() {
        numberPickerDialog.show()
    }

    private fun setUpCalendar() {
        val builder = MaterialDatePicker.Builder.datePicker()
            .setSelection(today)
        datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener {
            viewModel.update(it as Long)
        }
    }

    private fun showCalendar() {
        datePicker.show(childFragmentManager, datePicker.toString())
    }
}