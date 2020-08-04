package com.nikasov.winstarstest.ui.fragment.dayoff

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikasov.winstarstest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DayOffFragment : Fragment(R.layout.fragment_new_task) {

    private val viewModel : DayOffViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}