package com.nikasov.winstarstest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.model.StatisticModel
import kotlinx.android.synthetic.main.item_action.view.title
import kotlinx.android.synthetic.main.item_statistic.view.*

class StatisticAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<StatisticModel>() {
        override fun areItemsTheSame(oldItem: StatisticModel, newItem: StatisticModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: StatisticModel, newItem: StatisticModel): Boolean {
            return oldItem.title == newItem.title
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return ProfileActionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_statistic,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProfileActionViewHolder -> {
                holder.bind(differ.currentList[position], (itemCount - 1 == position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<StatisticModel>) {
        differ.submitList(list)
    }

    class ProfileActionViewHolder constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: StatisticModel, isLast : Boolean) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.title.text = item.title
            itemView.value.text = item.value
            if (isLast)
                itemView.star.visibility = View.VISIBLE
            else
                itemView.star.visibility = View.GONE
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: StatisticModel)
    }
}

