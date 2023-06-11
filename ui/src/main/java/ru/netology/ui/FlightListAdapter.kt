package ru.netology.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.domain.model.Flight
import ru.netology.ui.databinding.CardTravelItemBinding

import java.time.format.DateTimeFormatter

class FlightListAdapter(
    private val onFlightItemClickListener: OnFlightItemClickListener
) : ListAdapter<Flight, FlightListAdapter.ViewHolder>(Diff()) {

    inner class ViewHolder(private val binding: CardTravelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var isClicked = false

        fun bind(flight: Flight) {

            val anim = AnimationUtils.loadAnimation(binding.root.context, R.anim.like)

            binding.travelInfo.root.transitionName = "transition_$adapterPosition"

            binding.travelInfo.apply {
                startCode.text = flight.startLocationCode
                startCity.text = flight.startCity
                startDate.text = flight.startDate.format(DateTimeFormatter.ofPattern("E dd.MM"))
                endCode.text = flight.endLocationCode
                endCity.text = flight.endCity
                endDate.text = flight.endDate.format(DateTimeFormatter.ofPattern("E dd.MM"))
                favorite.isChecked = flight.isLiked

                if (favorite.isChecked && isClicked) {
                    favorite.startAnimation(anim)
                    isClicked = false
                }

                favorite.setOnClickListener {
                    isClicked = true
                    onFlightItemClickListener.onLikeClicked(flight.searchToken)
                }

                root.setOnClickListener {
                    onFlightItemClickListener.onItemClicked(flight, binding.travelInfo.root)
                }

                price.text =
                    flight.price.toAmount(binding.root.context.getString(R.string.currency))
            }
        }
    }

    class Diff : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem.searchToken == newItem.searchToken
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