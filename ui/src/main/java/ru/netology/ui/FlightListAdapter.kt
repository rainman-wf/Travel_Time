package ru.netology.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.ui.databinding.CardTravelItemBinding

class FlightListAdapter : ListAdapter<LikableFlight, FlightListAdapter.ViewHolder>(Diff()) {

    inner class ViewHolder(private val binding: CardTravelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flight: LikableFlight) {
            binding.textView.text = flight.flight.searchToken
        }
    }

    class Diff : DiffUtil.ItemCallback<LikableFlight>() {
        override fun areItemsTheSame(oldItem: LikableFlight, newItem: LikableFlight): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LikableFlight, newItem: LikableFlight): Boolean {
            return oldItem.flight.searchToken == newItem.flight.searchToken
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardTravelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}