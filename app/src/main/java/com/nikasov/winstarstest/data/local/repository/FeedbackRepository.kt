package com.nikasov.winstarstest.data.local.repository

import android.content.Context
import com.nikasov.winstarstest.data.local.model.FeedbackStatModel
import javax.inject.Inject

class FeedbackRepository @Inject constructor (
    private var application: Context
) {
    fun getFeedbackStatList() : List<FeedbackStatModel> {
        val list = arrayListOf<FeedbackStatModel>()

        list.apply {
            add(FeedbackStatModel(
                "Collective",
                4.5f
            ))
            add(FeedbackStatModel(
                "Management",
                4.5f
            ))
            add(FeedbackStatModel(
                "Work",
                4.5f
            ))
            add(FeedbackStatModel(
                "Salary",
                4.5f
            ))
            add(FeedbackStatModel(
                "Life",
                4.5f
            ))
        }

        return list
    }
}