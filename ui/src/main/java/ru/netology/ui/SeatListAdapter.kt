package ru.netology.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.domain.model.Seat
import ru.netology.ui.databinding.CardSeatItemBinding

class SeatListAdapter (
    private val onItemClickListener: (Seat) -> Unit
) : ListAdapter<Seat, SeatListAdapter.ViewHolder>(Diff()) {

    inner class ViewHolder(private val binding: CardSeatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(seat: Seat) {

            binding.passengerType.text = seat.passengerType // need convert to Passenger type codes
            binding.count.text = seat.count.toString()

            binding.root.setOnClickListener {
                onItemClickListener(seat)
            }
        }
    }

    class Diff : DiffUtil.ItemCallback<Seat>() {
        override fun areItemsTheSame(oldItem: Seat, newItem: Seat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Seat, newItem: Seat): Boolean {
            return oldItem.passengerType == newItem.passengerType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardSeatItemBinding.inflate(
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