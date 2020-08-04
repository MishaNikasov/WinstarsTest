package com.nikasov.winstarstest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.FeedbackStatModel
import kotlinx.android.synthetic.main.item_feedback_stat.view.*

class FeedbackStatAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FeedbackStatModel>() {

        override fun areItemsTheSame(oldItem: FeedbackStatModel, newItem: FeedbackStatModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedbackStatModel, newItem: FeedbackStatModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedbackStatViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_feedback_stat,
                    parent,
                    false
                ), interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FeedbackStatViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<FeedbackStatModel>) {
        differ.submitList(list)
    }

    class FeedbackStatViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: FeedbackStatModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.title.text = item.title
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: FeedbackStatModel)
    }
}

