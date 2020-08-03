package com.nikasov.winstarstest.ui.fragment.tasks.fromme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.task.HeaderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_from_me.*

@AndroidEntryPoint
class FromMeFragment : Fragment(R.layout.fragment_from_me) {

    private val fromMeViewModel : FromMeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {

        setUpList()
    }

    private fun setUpList() {

        val taskAdapter = HeaderAdapter()

        taskRecycler.adapter = taskAdapter

        fromMeViewModel.list.observe(viewLifecycleOwner, Observer { list ->
            taskAdapter.submitList(list)
        })
    }
}