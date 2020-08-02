package com.nikasov.winstarstest.data.local.repository

import androidx.lifecycle.MutableLiveData
import com.nikasov.winstarstest.data.local.model.HeaderTaskModel
import com.nikasov.winstarstest.data.local.model.SubTaskModel
import com.nikasov.winstarstest.data.local.model.TaskModel
import java.util.*
import javax.inject.Inject

class TaskRepository @Inject constructor(

) {

    fun getTaskList() : MutableLiveData<List<HeaderTaskModel>> {

        val fullTaskList = arrayListOf<HeaderTaskModel>()

        val modelsList = arrayListOf<TaskModel>()
        val hiddenTaskList = arrayListOf<SubTaskModel>()

        hiddenTaskList.add(SubTaskModel("Task number 1"))
        hiddenTaskList.add(SubTaskModel("Task number 1.2"))
        hiddenTaskList.add(SubTaskModel("Task number 1.3"))

        modelsList.add(TaskModel(
            "Task number 1. Correct the front end of the page \"Financial Management\"",
            "Very interesting additional information, a detailed description of the task.",
            Calendar.getInstance().time,
            "Alyosha Popovich",
            "Hanna Ivanchuk",
            hiddenTaskList
        ))

        modelsList.add(TaskModel(
            "Task number 2",
            null,
            null,
            "Alyosha Popovich",
            "Hanna Ivanchuk",
            hiddenTaskList
        ))


        modelsList.add(TaskModel(
            "Task number 3",
            null,
            null,
            "Alyosha Popovich",
            "Hanna Ivanchuk",
            hiddenTaskList
        ))

        fullTaskList.add(
            HeaderTaskModel(
            "To do (8)", modelsList
        ))

        fullTaskList.add(HeaderTaskModel(
            "Agreement (8)", modelsList
        ))

        fullTaskList.add(HeaderTaskModel(
            "Completed (8)", modelsList
        ))

        return MutableLiveData(fullTaskList)
    }
}