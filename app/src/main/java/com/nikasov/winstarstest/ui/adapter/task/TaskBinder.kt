package com.nikasov.winstarstest.ui.adapter.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
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
            itemView.owner.text = item.owner
            itemView.member.text = item.member

            if (item.date !== null) {
                itemView.date.visibility = View.VISIBLE
                itemView.date.text = DateUtils.formatDate(item.date!!, itemView.context.resources.getString(R.string.day_format))
            } else {
                itemView.date.visibility = View.GONE
            }

            if (item.summary !== null) {
                itemView.summary.visibility = View.VISIBLE
                itemView.summary.text = item.title
            } else {
                itemView.summary.visibility = View.GONE
            }

            itemView.arrow.setOnClickListener {
                toggleSectionExpansion()
                checkIsExpansion(isSectionExpanded)
            }
            checkIsExpansion(isSectionExpanded)
        }

        private fun checkIsExpansion(isExpansion: Boolean) {
            val view = itemView as MotionLayout
            if (isExpansion) {
                itemView.transitionToState(R.id.bottomViewRight)
            } else {
                itemView.transitionToState(R.id.bottomViewStart)
                itemView.bottomDivider.visibility = View.VISIBLE
            }
        }
    }
}