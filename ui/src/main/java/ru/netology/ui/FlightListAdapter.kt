package ru.netology.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.ui.databinding.CardTravelItemBinding
import java.time.format.DateTimeFormatter

class FlightListAdapter(
    private val onFlightItemClickListener: OnFlightItemClickListener
) : ListAdapter<LikableFlight, FlightListAdapter.ViewHolder>(Diff()) {

    inner class ViewHolder(private val binding: CardTravelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flight: LikableFlight) {

            binding.apply {
                startCode.text = flight.flight.startLocationCode
                startCity.text = flight.flight.startCity
                startDate.text = flight.flight.startDate.format(DateTimeFormatter.ofPattern("E dd.MM"))
                endCode.text = flight.flight.endLocationCode
                endCity.text = flight.flight.endCity
                endDate.text = flight.flight.endDate.format(DateTimeFormatter.ofPattern("E dd.MM"))
                favorite.isChecked = flight.liked
                favorite.setOnClickListener {
                    onFlightItemClickListener.onLikeClicked(flight.flight.searchToken)
                }
                root.setOnClickListener {
                    onFlightItemClickListener.onItemClicked(flight.flight.searchToken)
                }
                price.text = flight.flight.price.toAmount()
            }
        }

        private fun Int.toAmount() : String{
            return "$this ${binding.root.context.resources.getString(R.string.currency)}"
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