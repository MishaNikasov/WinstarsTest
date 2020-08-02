package com.nikasov.winstarstest.ui.adapter.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.SubTaskModel
import kotlinx.android.synthetic.main.item_sub_task.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class SubTaskBinder : ItemBinder<SubTaskModel, SubTaskBinder.TaskViewHolder>() {

    override fun bindViewHolder(holder: TaskViewHolder?, item: SubTaskModel?) {
        holder?.bind()
    }

    override fun createViewHolder(parent: ViewGroup?): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_sub_task,
                parent,
                false
            )
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is SubTaskModel
    }

    class TaskViewHolder (itemView: View) : ItemViewHolder<SubTaskModel>(itemView) {
        fun bind() {
            itemView.text.text = item.task
            adapterPosition
        }
    }
}