package com.nikasov.winstarstest.ui.adapter.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.TaskModel
import com.nikasov.winstarstest.utils.DateUtils
import kotlinx.android.synthetic.main.item_task.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class TaskBinder : ItemBinder<TaskModel, TaskBinder.TaskViewHolder>() {

    override fun bindViewHolder(holder: TaskViewHolder?, item: TaskModel?) {
        holder?.bind()
    }

    override fun createViewHolder(parent: ViewGroup?): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_task,
                parent,
                false
            )
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is TaskModel
    }

    class TaskViewHolder (itemView: View) : ItemViewHolder<TaskModel>(itemView) {
        fun bind() {
            itemView.title.text = item.title
            itemView.summary.text = item.title
            itemView.date.text = DateUtils.formatDate(item.date, itemView.context.resources.getString(R.string.day_format))
            itemView.owner.text = item.owner
            itemView.member.text = item.member
        }
    }
}