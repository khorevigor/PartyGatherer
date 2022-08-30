package com.dsphoenix.partygatherer.ui.events_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dsphoenix.partygatherer.databinding.ViewHolderEventBinding
import com.dsphoenix.partygatherer.model.Event

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private var items: List<Event> = emptyList()

    private var onItemClickListener: ((Event) -> Unit)? = null

    private fun getItem(position: Int) = items[position]
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolderEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(newItems: List<Event>) {
        val diff = DiffUtil.calculateDiff(EventsCallback(items, newItems))
        diff.dispatchUpdatesTo(this)
        items = newItems
    }

    fun setOnItemClickListener(listener: (Event) -> Unit) {
        onItemClickListener = listener
    }

    class ViewHolder(
        private val binding: ViewHolderEventBinding,
        private val eventClickListener: ((Event) -> Unit)?,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.apply {
                tvTitle.text = event.title
                tvDate.text = event.timestamp.toString()
            }
        }
    }
}

class EventsCallback(
    private val oldList: List<Event>,
    private val newList: List<Event>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}
