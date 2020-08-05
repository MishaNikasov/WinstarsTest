package com.nikasov.winstarstest.ui.fragment.tasks.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.task.HeaderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_from_me.*

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_from_me) {

    private val taskViewModel : TaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        setUpUI()
    }

    private fun setUpUI() {

        setUpList()
    }

    private fun setUpList() {

        val taskAdapter = HeaderAdapter()

        taskRecycler.adapter = taskAdapter

        taskViewModel.list.observe(viewLifecycleOwner, Observer { list ->
            taskAdapter.submitList(list)
        })
    }
}