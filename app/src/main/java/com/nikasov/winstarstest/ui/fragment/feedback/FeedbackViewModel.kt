package com.nikasov.winstarstest.ui.fragment.feedback

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.model.FeedbackStatModel
import com.nikasov.winstarstest.data.local.repository.FeedbackRepository

class FeedbackViewModel @ViewModelInject constructor(
    private val feedbackRepository: FeedbackRepository
) : ViewModel() {

    fun getFeedbackStat() : List<FeedbackStatModel> {
        return feedbackRepository.getFeedbackStatList()
    }

}