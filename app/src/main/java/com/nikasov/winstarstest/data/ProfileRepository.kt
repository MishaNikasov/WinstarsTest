package com.nikasov.winstarstest.data

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.nikasov.winstarstest.R
import javax.inject.Inject

class ProfileRepository @Inject constructor (private var application: Context) {

    fun getAllProfileActions() : MutableLiveData<List<ProfileActionModel>> {
        val list = arrayListOf<ProfileActionModel>()
        list.add(ProfileActionModel(
            R.drawable.ic_tasks,
            application.resources.getString(R.string.tasks),
            true))
        list.add(ProfileActionModel(
            R.drawable.ic_access_time,
            application.resources.getString(R.string.time_tracking),
            true))
        list.add(ProfileActionModel(
            R.drawable.ic_alarm,
            application.resources.getString(R.string.day_off),
            true))
        list.add(ProfileActionModel(
            R.drawable.ic_date,
            application.resources.getString(R.string.job_offer),
            true))
        list.add(ProfileActionModel(
            R.drawable.ic_bonuses,
            application.resources.getString(R.string.bonuses),
            true))
        list.add(ProfileActionModel(
            R.drawable.ic_winstars_open,
            application.resources.getString(R.string.winstars_open),
            true))
        list.add(ProfileActionModel(
            R.drawable.ic_star_half,
            application.resources.getString(R.string.feedback),
            true))
        return MutableLiveData(list)
    }

}