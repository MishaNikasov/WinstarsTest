package com.nikasov.winstarstest.ui.adapter.bonuses

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.bonus.BonusModel
import com.nikasov.winstarstest.utils.DateUtils
import kotlinx.android.synthetic.main.item_bonus.view.*
import java.lang.StringBuilder

class BonusesAdapter(private val interaction: Interaction? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BonusModel>() {

        override fun areItemsTheSame(oldItem: BonusModel, newItem: BonusModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BonusModel, newItem: BonusModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedbackStatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bonus,
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

    fun submitList(list: List<BonusModel>) {
        differ.submitList(list)
    }

    class FeedbackStatViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: BonusModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            val amountBuilder = StringBuilder()


            if (item.amount > 0) {
                amountBuilder.append("+ ")
                amountBuilder.append("${item.amount}")
                itemView.title.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
            } else {
                amountBuilder.append("- ")
                amountBuilder.append("${item.amount*-1}")
                itemView.title.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
            }

            if (item.isExpect) {
                itemView.expect.visibility = View.VISIBLE
                itemView.date.setTextColor(ContextCompat.getColor(itemView.context, R.color.gold))
                itemView.title.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_color_dark))
            } else {
                itemView.expect.visibility = View.GONE
                itemView.date.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_color_dark))
            }

            amountBuilder.append(" бонусів")

            itemView.title.text = amountBuilder.toString()
            itemView.description.text = item.description
            itemView.date.text = DateUtils.formatDate(item.date, itemView.context.resources.getString(R.string.date_with_year))
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: BonusModel)
    }
}

