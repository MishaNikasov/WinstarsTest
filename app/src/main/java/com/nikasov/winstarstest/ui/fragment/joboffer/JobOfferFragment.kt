package com.nikasov.winstarstest.ui.fragment.joboffer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.JobOfferAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_job_offer.*

@AndroidEntryPoint
class JobOfferFragment : Fragment(R.layout.fragment_job_offer) {

    private val viewModel : JobOfferViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        setUpUI()
    }

    private fun setUpUI() {
        setUpList()
    }

    private fun setUpList() {
        val messageAdapter = JobOfferAdapter()
        messageAdapter.submitList(viewModel.getJobOfferList())
        jobOfferRecycler.apply {
            adapter = messageAdapter
        }
    }

}