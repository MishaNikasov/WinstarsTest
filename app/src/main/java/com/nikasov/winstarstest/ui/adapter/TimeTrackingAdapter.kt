package com.nikasov.winstarstest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingModel
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingTypes
import kotlinx.android.synthetic.main.item_time_tracking.view.*

class TimeTrackingAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<TimeTrackingModel>() {
        override fun areItemsTheSame(oldItem: TimeTrackingModel, newItem: TimeTrackingModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: TimeTrackingModel, newItem: TimeTrackingModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ProfileActionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_time_tracking,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProfileActionViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TimeTrackingModel>) {
        differ.submitList(list)
    }

    class ProfileActionViewHolder constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TimeTrackingModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            val timeTxt = "${item.time} ${itemView.resources.getString(R.string.h)}"
            itemView.time.text = timeTxt
            itemView.text.setText(item.text)
            val data = TimeTrackingTypes.values()
            itemView.spinner.attachDataSource(data.toList())
            itemView.spinner.selectedIndex = data.indexOf(item.type)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: TimeTrackingModel)
    }
}

