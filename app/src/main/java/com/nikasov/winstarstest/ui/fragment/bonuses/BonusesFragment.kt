package com.nikasov.winstarstest.ui.fragment.bonuses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikasov.winstarstest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusesFragment : Fragment(R.layout.fragment_new_task) {

    private val viewModel : BonusesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}