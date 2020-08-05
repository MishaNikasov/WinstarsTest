package com.nikasov.winstarstest.ui.fragment.bonuses.exchange

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.bonuses.BonusesExchangeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_bonuses_exchange.*

@AndroidEntryPoint
class BonusesExchangeFragment : Fragment(R.layout.fragment_bonuses_exchange) {

    private val viewModel : BonusesExchangeViewModel by viewModels()

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
        val messageAdapter = BonusesExchangeAdapter()
        messageAdapter.submitList(viewModel.getBonusesExchange())
        bonusesExchangeRecycler.apply {
            adapter = messageAdapter
        }
    }
}