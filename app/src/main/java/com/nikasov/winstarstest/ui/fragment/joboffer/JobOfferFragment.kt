package com.nikasov.winstarstest.ui.fragment.joboffer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nikasov.winstarstest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobOfferFragment : Fragment(R.layout.fragment_new_task) {

    private val viewModel : JobOfferViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}