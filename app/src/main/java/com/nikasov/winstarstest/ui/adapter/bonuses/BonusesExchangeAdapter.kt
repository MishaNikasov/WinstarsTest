package com.nikasov.winstarstest.ui.adapter.bonuses

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.BonusExchangeModel
import kotlinx.android.synthetic.main.item_bonus_exchange.view.*

class BonusesExchangeAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BonusExchangeModel>() {

        override fun areItemsTheSame(oldItem: BonusExchangeModel, newItem: BonusExchangeModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BonusExchangeModel, newItem: BonusExchangeModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedbackStatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bonus_exchange,
                parent,
                false
            ), interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FeedbackStatViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<BonusExchangeModel>) {
        differ.submitList(list)
    }

    class FeedbackStatViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: BonusExchangeModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.description.text = item.description
            itemView.amount.text = "${item.amount}"
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: BonusExchangeModel)
    }
}

