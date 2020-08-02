package com.nikasov.winstarstest.data.local.repository

import androidx.lifecycle.MutableLiveData
import com.nikasov.winstarstest.data.local.model.FullTaskModel
import com.nikasov.winstarstest.data.local.model.TaskModel
import java.util.*
import javax.inject.Inject

class TaskRepository @Inject constructor(

) {

    fun getTaskList() : MutableLiveData<List<FullTaskModel>> {

        val fullTaskList = arrayListOf<FullTaskModel>()

        val modelsList = arrayListOf<TaskModel>()
        val hiddenTaskList = arrayListOf<String>()

        hiddenTaskList.add("Task number 1")
        hiddenTaskList.add("Task number 1.2")
        hiddenTaskList.add("Task number 1.3")

        modelsList.add(TaskModel(
            "Task number 1. Correct the front end of the page \"Financial Management\"",
            "Very interesting additional information, a detailed description of the task.",
            Calendar.getInstance().time,
            "Alyosha Popovich",
            "Hanna Ivanchuk",
            hiddenTaskList
        ))

        fullTaskList.add(
            FullTaskModel(
            "Task 1", modelsList
        ))

        fullTaskList.add(FullTaskModel(
            "Task 2", modelsList
        ))

        fullTaskList.add(FullTaskModel(
            "Task 3", modelsList
        ))

        return MutableLiveData(fullTaskList)
    }
}