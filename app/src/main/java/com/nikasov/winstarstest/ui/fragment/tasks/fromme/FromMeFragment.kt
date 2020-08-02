package com.nikasov.winstarstest.ui.fragment.tasks.fromme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.FullTaskModel
import com.nikasov.winstarstest.ui.adapter.task.FullTaskBinder
import com.nikasov.winstarstest.ui.adapter.task.TaskBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_from_me.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode

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

        val taskAdapter = MultiViewAdapter().apply {
            registerItemBinders(FullTaskBinder(), TaskBinder())
            setExpansionMode(Mode.SINGLE)
            setSectionExpansionMode(Mode.SINGLE)
        }

        taskRecycler.adapter = taskAdapter

        val listSection = ListSection<FullTaskModel>()

        fromMeViewModel.list.observe(viewLifecycleOwner, Observer {
            listSection.addAll(it)
            taskAdapter.addSection(listSection)
        })
    }

}