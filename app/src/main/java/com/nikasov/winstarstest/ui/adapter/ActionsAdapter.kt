package com.nikasov.winstarstest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.model.ActionModel
import kotlinx.android.synthetic.main.item_action.view.*

class ActionsAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ActionModel>() {
        override fun areItemsTheSame(oldItem: ActionModel, newItem: ActionModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ActionModel, newItem: ActionModel): Boolean {
            return oldItem.title == newItem.title
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ProfileActionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_action,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProfileActionViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ActionModel>) {
        differ.submitList(list)
    }

    class ProfileActionViewHolder constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ActionModel) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            val actionIco =
                if (item.isAdd)
                    R.drawable.ic_add
                else
                    R.drawable.ic_arrow_forward

            itemView.title.text = item.title
            itemView.icon.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.icon))
            itemView.action.setImageDrawable(ContextCompat.getDrawable(itemView.context, actionIco))
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ActionModel)
    }
}

