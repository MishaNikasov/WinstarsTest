package com.nikasov.winstarstest.data.local.model.task

data class HeaderTaskModel (
    var title : String,
    var taskList : List<TaskModel>,

    var isExpanded: Boolean = false
)