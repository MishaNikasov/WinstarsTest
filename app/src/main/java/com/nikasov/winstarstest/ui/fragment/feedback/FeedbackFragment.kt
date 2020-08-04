package com.nikasov.winstarstest.ui.fragment.feedback

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.FeedbackStatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_feedback.*

@AndroidEntryPoint
class FeedbackFragment : Fragment(R.layout.fragment_feedback) {

    private val viewModel : FeedbackViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        setUpList()
    }

    private fun setUpList() {
        val feedbackStatAdapter = FeedbackStatAdapter()
        feedbackStatAdapter.submitList(viewModel.getFeedbackStat())
        statRecycler.apply {
            adapter = feedbackStatAdapter
        }
    }

}