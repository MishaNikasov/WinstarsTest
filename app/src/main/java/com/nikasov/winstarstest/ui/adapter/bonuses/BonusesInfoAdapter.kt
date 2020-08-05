package com.nikasov.winstarstest.ui.adapter.bonuses

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.bonus.BonusInfoModel
import kotlinx.android.synthetic.main.item_bonus_info.view.*

class BonusesInfoAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BonusInfoModel>() {

        override fun areItemsTheSame(oldItem: BonusInfoModel, newItem: BonusInfoModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BonusInfoModel, newItem: BonusInfoModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedbackStatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bonus_info,
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

    fun submitList(list: List<BonusInfoModel>) {
        differ.submitList(list)
    }

    class FeedbackStatViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: BonusInfoModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            val amountTxt : String = if (item.amount > 0) {
                "+ ${item.amount}"
            } else {
                "- ${item.amount*-1}"
            }

            itemView.amount.text = amountTxt
            itemView.title.text = item.title
            itemView.description.text = item.description

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: BonusInfoModel)
    }
}

