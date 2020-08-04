package com.nikasov.winstarstest.data.local.model.task

import com.nikasov.winstarstest.data.local.model.task.SubTaskModel
import java.util.*

data class TaskModel(
    var title: String,
    var summary: String?,
    var date: Date?,
    var owner: String,
    var member: String,
    var tasks: List<SubTaskModel>,

    var isExpanded: Boolean = false
)