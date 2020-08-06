package com.nikasov.winstarstest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.DayOffModel
import com.nikasov.winstarstest.data.local.model.DayOffState
import com.nikasov.winstarstest.utils.DateUtils
import kotlinx.android.synthetic.main.item_day_off.view.*
import java.lang.StringBuilder

class DayOffAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DayOffModel>() {

        override fun areItemsTheSame(oldItem: DayOffModel, newItem: DayOffModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayOffModel, newItem: DayOffModel): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DayOffViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_day_off,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DayOffViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<DayOffModel>) {
        differ.submitList(list)
    }

    class DayOffViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DayOffModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            if (item.isExpect) {
                itemView.expect.visibility = View.VISIBLE
            } else {
                itemView.expect.visibility = View.GONE
            }

            val dateRange = StringBuilder()


            item.dateRange.onEach {
                dateRange.append(DateUtils.formatDate(it, itemView.context.resources.getString(R.string.date_without_year)))
            }

            itemView.dateRange.text = dateRange.toString()
            itemView.spinner.text = " "
            itemView.description.text = item.description

            val color: Int

            when (item.state) {
                DayOffState.SICKEN -> {
                    color = ContextCompat.getColor(itemView.context, R.color.red)
                }
                DayOffState.REST -> {
                    color = ContextCompat.getColor(itemView.context, R.color.text_color_dark)
                }
                DayOffState.BUSY-> {
                    color = ContextCompat.getColor(itemView.context, R.color.green)
                }
                DayOffState.STUDY  -> {
                    color = ContextCompat.getColor(itemView.context, R.color.gold)
                }
            }

            itemView.dateRange.setTextColor(color)

            val data = DayOffState.values()
            itemView.spinner.attachDataSource(data.toList())
            itemView.spinner.selectedIndex = data.indexOf(item.state)

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: DayOffModel)
    }
}

