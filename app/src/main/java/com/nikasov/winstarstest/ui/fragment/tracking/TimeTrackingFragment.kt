package com.nikasov.winstarstest.ui.fragment.tracking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.datepicker.MaterialDatePicker
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.TimeTrackingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_time_tracking.*
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TimeTrackingFragment : Fragment(R.layout.fragment_time_tracking) {
//todo: починить спинеры, добавить по установке времени добавление
    private val viewModel : TimeTrackingViewModel by viewModels()
    private lateinit var datePicker : MaterialDatePicker<*>
    private var today : Long = MaterialDatePicker.todayInUtcMilliseconds()

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
        viewModel.update(today)
        viewModel.currentDate.observe(viewLifecycleOwner, Observer { date ->
            dayText.text = getDayText(date)
            yearText.text = getYearText(date)
        })
        calendarBtn.setOnClickListener {
            showCalendar()
        }
        timeBtn.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showTimePicker() {

    }

    private fun setUpCalendar() {
        val builder = MaterialDatePicker.Builder.datePicker()
            .setSelection(today)
        datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener {
            viewModel.update(it as Long)
            viewModel.addTimeTracking(Date(it as Long), "OPA")
        }
    }

    private fun showCalendar() {
        datePicker.show(childFragmentManager, datePicker.toString())
    }

    private fun getDayText(date : Date) : String{
        val pattern = "EEEE d"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    private fun getYearText(date : Date) : String {
        val pattern = "yyyy MMMM"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(date)
    }

}