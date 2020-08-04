package com.nikasov.winstarstest.ui.adapter.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.task.HeaderTaskModel
import kotlinx.android.synthetic.main.item_task_header.view.*


class HeaderAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HeaderTaskModel>() {

        override fun areItemsTheSame(oldItem: HeaderTaskModel, newItem: HeaderTaskModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HeaderTaskModel,
            newItem: HeaderTaskModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return HeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_task_header,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
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

    fun submitList(list: List<HeaderTaskModel>) {
        differ.submitList(list)
    }

    class HeaderViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: HeaderTaskModel) = with(itemView) {

            itemView.title.text = item.title

            val adapter = TaskAdapter()
            adapter.submitList(item.taskList)

            itemView.taskRecycler.adapter = adapter

            val state =  item.isExpanded

            itemView.taskRecycler.visibility = if (state) View.VISIBLE else View.GONE
            itemView.arrow.rotation = if (!state) 0f else 180f
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: HeaderTaskModel)
    }

}

