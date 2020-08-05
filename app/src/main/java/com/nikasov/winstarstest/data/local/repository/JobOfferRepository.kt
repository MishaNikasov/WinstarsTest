package com.nikasov.winstarstest.data.local.repository

import com.nikasov.winstarstest.data.local.model.JobOfferModel
import javax.inject.Inject

class JobOfferRepository @Inject constructor(

) {
    fun getJobOfferList(): List<JobOfferModel> {
        val list = arrayListOf<JobOfferModel>()

        list.apply {
            add(JobOfferModel(
                "Day-off",
                "Text",
                "Text text text text text"
            ))
            add(JobOfferModel(
                "Timeline",
                "Text",
                "Text text text text text"
            ))
            add(JobOfferModel(
                "Hours",
                "Text",
                "Text text text text text"
            ))
        }

        return list
    }
}