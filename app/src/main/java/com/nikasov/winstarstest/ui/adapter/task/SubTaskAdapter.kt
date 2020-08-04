package com.nikasov.winstarstest.ui.adapter.task

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.task.SubTaskModel
import kotlinx.android.synthetic.main.item_sub_task.view.*

class SubTaskAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SubTaskModel>() {

        override fun areItemsTheSame(oldItem: SubTaskModel, newItem: SubTaskModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SubTaskModel,
            newItem: SubTaskModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SubTaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sub_task,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SubTaskViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<SubTaskModel>) {
        differ.submitList(list)
    }

    class SubTaskViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SubTaskModel) = with(itemView) {
            itemView.text.text = item.task
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: SubTaskModel)
    }
}

