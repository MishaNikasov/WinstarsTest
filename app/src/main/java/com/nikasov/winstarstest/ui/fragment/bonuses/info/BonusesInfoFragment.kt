package com.nikasov.winstarstest.ui.fragment.bonuses.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.bonuses.BonusesInfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bonuses_info.*

@AndroidEntryPoint
class BonusesInfoFragment : Fragment(R.layout.fragment_bonuses_info) {

    private val viewModel : BonusesInfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        setUpUI()
    }

    private fun setUpUI() {
        forwardArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setUpList()
    }

    private fun setUpList() {
        val messageAdapter = BonusesInfoAdapter()
        messageAdapter.submitList(viewModel.getBonusesExchange())
        bonusesInfoRecycler.apply {
            adapter = messageAdapter
        }
    }
}