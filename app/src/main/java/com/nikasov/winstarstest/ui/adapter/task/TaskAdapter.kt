package com.nikasov.winstarstest.ui.adapter.task

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.task.TaskModel
import com.nikasov.winstarstest.utils.DateUtils
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TaskModel>() {

        override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TaskModel,
            newItem: TaskModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TaskViewHolder -> {
                holder.bind(differ.currentList[position])

                val item =  differ.currentList[position]

                holder.itemView.setOnClickListener {
                    item.isExpanded = !item.isExpanded
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TaskModel>) {
        differ.submitList(list)
    }

    class TaskViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TaskModel) = with(itemView) {

            itemView.title.text = item.title
            itemView.owner.text = item.owner
            itemView.member.text = item.member

            if (item.date !== null) {
                itemView.date.visibility = View.VISIBLE
                itemView.date.text = DateUtils.formatDate(item.date!!, itemView.context.resources.getString(R.string.task_format))
            } else {
                itemView.date.visibility = View.GONE
            }

            if (item.summary !== null) {
                itemView.summary.visibility = View.VISIBLE
                itemView.summary.text = item.summary
            } else {
                itemView.summary.visibility = View.GONE
            }

            val adapter = SubTaskAdapter()
            adapter.submitList(item.tasks)

            itemView.subTaskRecycler.adapter = adapter

            val state =  item.isExpanded

            itemView.subTaskRecycler.visibility = if (state) View.VISIBLE else View.GONE
            itemView.arrow.rotation = if (!state) 0f else 180f
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: TaskModel)
    }
}

