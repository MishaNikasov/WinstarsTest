package com.nikasov.winstarstest.ui.fragment.bonuses.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.bonuses.BonusesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bonuses.*

@AndroidEntryPoint
class BonusesFragment : Fragment(R.layout.fragment_bonuses) {

    private val viewModel : BonusesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        setUpUi()
    }

    private fun setUpUi() {
        setUpList()

        bonusesExchange.setOnClickListener {
            findNavController().navigate(R.id.toBonusesExchangeFragment)
        }
    }

    private fun setUpList() {
        val feedbackStatAdapter =
            BonusesAdapter()
        feedbackStatAdapter.submitList(viewModel.getBonuses())
        bonusesRecycler.apply {
            adapter = feedbackStatAdapter
        }
    }

}