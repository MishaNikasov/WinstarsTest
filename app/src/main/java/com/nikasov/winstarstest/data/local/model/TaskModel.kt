package com.nikasov.winstarstest.data.local.model

import java.util.*

data class TaskModel(
    var title: String,
    var summary: String?,
    var date: Date?,
    var owner: String,
    var member: String,
    var tasks: List<SubTaskModel>
)