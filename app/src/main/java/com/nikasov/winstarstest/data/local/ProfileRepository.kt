package com.nikasov.winstarstest.data.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.ActionModel
import com.nikasov.winstarstest.data.local.model.StatisticModel
import javax.inject.Inject

class ProfileRepository @Inject constructor (
    private var application: Context
) {

    fun getAllProfileActions() : MutableLiveData<List<ActionModel>> {
        val list = arrayListOf<ActionModel>()
        list.add(ActionModel(
            R.drawable.ic_tasks,
            application.resources.getString(R.string.tasks),
            true))
        list.add(ActionModel(
            R.drawable.ic_access_time,
            application.resources.getString(R.string.time_tracking),
            true))
        list.add(ActionModel(
            R.drawable.ic_alarm,
            application.resources.getString(R.string.day_off),
            true))
        list.add(ActionModel(
            R.drawable.ic_date,
            application.resources.getString(R.string.job_offer),
            false))
        list.add(ActionModel(
            R.drawable.ic_bonuses,
            application.resources.getString(R.string.bonuses),
            false))
        list.add(ActionModel(
            R.drawable.ic_winstars_open,
            application.resources.getString(R.string.winstars_open),
            false))
        list.add(ActionModel(
            R.drawable.ic_star_half,
            application.resources.getString(R.string.feedback),
            false))
        return MutableLiveData(list)
    }

    fun getStatistic() : MutableLiveData<List<StatisticModel>>{
        val list = arrayListOf<StatisticModel>()
        list.add(StatisticModel("Earned this month", "0 $"))
        list.add(StatisticModel("Job Hours this month", "0 h"))
        list.add(StatisticModel("Rate per hour", "0 $"))
        list.add(StatisticModel("Remains Day-off", "0 d"))
        list.add(StatisticModel("Your rating", "0,0"))
        return MutableLiveData(list)
    }

}