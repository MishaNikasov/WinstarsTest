package com.nikasov.winstarstest.ui.fragment.tasks.task

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.Prefs
import com.nikasov.winstarstest.data.local.repository.TaskRepository

class TaskViewModel @ViewModelInject constructor(
    private val taskRepository: TaskRepository,
    private val prefs: Prefs
) : ViewModel(){

    val list = taskRepository.getTaskList()

}