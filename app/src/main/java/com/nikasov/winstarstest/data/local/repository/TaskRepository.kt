package com.nikasov.winstarstest.data.local.repository

import androidx.lifecycle.MutableLiveData
import com.nikasov.winstarstest.data.local.model.task.HeaderTaskModel
import com.nikasov.winstarstest.data.local.model.task.SubTaskModel
import com.nikasov.winstarstest.data.local.model.task.TaskModel
import java.util.*
import javax.inject.Inject

class TaskRepository @Inject constructor(

) {

    fun getTaskList() : MutableLiveData<List<HeaderTaskModel>> {

        val fullTaskList = arrayListOf<HeaderTaskModel>()

        val modelsList = arrayListOf<TaskModel>()
        val hiddenTaskList = arrayListOf<SubTaskModel>()

        hiddenTaskList.add(
            SubTaskModel(
                "Task number 1"
            )
        )
        hiddenTaskList.add(
            SubTaskModel(
                "Task number 1.2"
            )
        )
        hiddenTaskList.add(
            SubTaskModel(
                "Task number 1.3"
            )
        )

        modelsList.add(
            TaskModel(
                "Task number 1. Correct the front end of the page \"Financial Management\"",
                "Very interesting additional information, a detailed description of the task.",
                Calendar.getInstance().time,
                "Alyosha Popovich",
                "Hanna Ivanchuk",
                hiddenTaskList
            )
        )

        modelsList.add(
            TaskModel(
                "Task number 2",
                null,
                null,
                "Alyosha Popovich",
                "Hanna Ivanchuk",
                hiddenTaskList
            )
        )

        modelsList.add(
            TaskModel(
                "Task number 3",
                null,
                null,
                "Alyosha Popovich",
                "Hanna Ivanchuk",
                hiddenTaskList
            )
        )
        val ar1 = arrayListOf<TaskModel>()
        ar1.addAll(modelsList)
        fullTaskList.add(
            HeaderTaskModel(
                "To do (8)",
                ar1
            )
        )
        val ar2 = arrayListOf<TaskModel>()
        ar2.addAll(modelsList)
        fullTaskList.add(
            HeaderTaskModel(
                "Agreement (8)",
                ar2
            )
        )
        val ar3 = arrayListOf<TaskModel>()
        ar3.addAll(modelsList)
        fullTaskList.add(
            HeaderTaskModel(
                "Completed (8)",
                ar3
            )
        )

        return MutableLiveData(fullTaskList)
    }
}