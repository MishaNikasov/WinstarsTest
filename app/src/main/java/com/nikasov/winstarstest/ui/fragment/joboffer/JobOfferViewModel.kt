package com.nikasov.winstarstest.ui.fragment.joboffer

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.model.JobOfferModel
import com.nikasov.winstarstest.data.local.repository.JobOfferRepository

class JobOfferViewModel @ViewModelInject constructor(
    private val jobOfferRepository: JobOfferRepository
) : ViewModel() {

    fun getJobOfferList(): List<JobOfferModel> {
        return jobOfferRepository.getJobOfferList()
    }

}