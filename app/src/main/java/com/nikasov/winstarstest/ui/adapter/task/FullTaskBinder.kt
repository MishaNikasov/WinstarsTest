package com.nikasov.winstarstest.ui.adapter.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.FullTaskModel
import kotlinx.android.synthetic.main.item_task_title.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class FullTaskBinder : ItemBinder<FullTaskModel, FullTaskBinder.TaskViewHolder>() {

    override fun bindViewHolder(holder: TaskViewHolder?, item: FullTaskModel?) {
        holder?.bind()
    }

    override fun createViewHolder(parent: ViewGroup?): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_task_title,
                parent,
                false
            )
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is FullTaskModel
    }

    class TaskViewHolder (itemView: View) : ItemViewHolder<FullTaskModel>(itemView) {
        fun bind() {
            itemView.title.text = item.title

            itemView.setOnClickListener {
                toggleItemExpansion()
                toggleSectionExpansion()
            }

            if(isItemExpanded) {
                itemView.ii.visibility = View.INVISIBLE
            } else {
                itemView.ii.visibility = View.VISIBLE
            }

            if (isSectionExpanded) {
                // Section is expanded.
            }
        }
    }
}