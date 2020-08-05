package com.nikasov.winstarstest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.JobOfferModel
import kotlinx.android.synthetic.main.item_job_offer.view.*

class JobOfferAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JobOfferModel>() {

        override fun areItemsTheSame(oldItem: JobOfferModel, newItem: JobOfferModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: JobOfferModel, newItem: JobOfferModel): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return JobOfferViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_job_offer,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JobOfferViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<JobOfferModel>) {
        differ.submitList(list)
    }

    class JobOfferViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: JobOfferModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.title.text = item.title
            itemView.name.text = item.name
            itemView.description.text = item.description
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: JobOfferModel)
    }
}

