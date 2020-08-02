package com.nikasov.winstarstest.ui.fragment.tasks.fromme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.SubTaskModel
import com.nikasov.winstarstest.data.local.model.TaskModel
import com.nikasov.winstarstest.ui.adapter.task.HeaderTaskBinder
import com.nikasov.winstarstest.ui.adapter.task.SubTaskBinder
import com.nikasov.winstarstest.ui.adapter.task.TaskBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_from_me.*
import mva2.adapter.HeaderSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.TreeSection
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
            registerItemBinders(
                HeaderTaskBinder(),
                TaskBinder(),
                SubTaskBinder()
            )
            setExpansionMode(Mode.SINGLE)
            setSectionExpansionMode(Mode.SINGLE)
        }

        taskRecycler.adapter = taskAdapter

        fromMeViewModel.list.observe(viewLifecycleOwner, Observer { list ->
            list.onEach { header ->
                val headerSection = TreeSection(header, false)

                header.taskList.onEach { task ->
                    val taskSection = TreeSection<TaskModel>(task, false)

                    task.tasks.onEach { subTask ->
                        val subTaskSection = TreeSection<SubTaskModel>(subTask, false)
                        taskSection.addSection(subTaskSection)
                    }
                    headerSection.addSection(taskSection)
                }
                taskAdapter.addSection(headerSection)
            }
        })
    }
}