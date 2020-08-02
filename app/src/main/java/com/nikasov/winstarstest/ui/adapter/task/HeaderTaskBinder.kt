package com.nikasov.winstarstest.ui.adapter.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.HeaderTaskModel
import kotlinx.android.synthetic.main.item_task_header.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HeaderTaskBinder : ItemBinder<HeaderTaskModel, HeaderTaskBinder.TaskViewHolder>() {

    override fun bindViewHolder(holder: TaskViewHolder?, item: HeaderTaskModel?) {
        holder?.bind()
    }

    override fun createViewHolder(parent: ViewGroup?): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_task_header,
                parent,
                false
            )
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is HeaderTaskModel
    }

    class TaskViewHolder (itemView: View) : ItemViewHolder<HeaderTaskModel>(itemView) {
        fun bind() {
            itemView.title.text = item.title

            itemView.setOnClickListener {
                toggleSectionExpansion()
            }
        }
    }
}