package com.nikasov.winstarstest.data.local.repository

import com.nikasov.winstarstest.data.local.model.DayOffModel
import com.nikasov.winstarstest.data.local.model.DayOffState
import java.util.*
import javax.inject.Inject

class DayOffRepository @Inject constructor(

){

    fun getDayOffList(): List<DayOffModel> {
        val list = arrayListOf<DayOffModel>()
        val dateRange = arrayListOf<Date>()

        dateRange.apply {
            add(Calendar.getInstance().time)
            add(Calendar.getInstance().time)
        }

        list.apply {
            add(
                DayOffModel(
                    dateRange,
                    DayOffState.SICKEN,
                    "Personal reason",
                    true
            ))
            add(
                DayOffModel(
                    dateRange,
                    DayOffState.REST,
                    "Passed exams for 2 weeks",
                    false
            ))
            add(
                DayOffModel(
                    dateRange,
                    DayOffState.BUSY,
                    "",
                    false
            ))
            add(
                DayOffModel(
                    dateRange,
                    DayOffState.BUSY,
                    "",
                    false
            ))
            add(
                DayOffModel(
                    dateRange,
                    DayOffState.SICKEN,
                    "Personal reason",
                    false
            ))
        }

        return list
    }

}