package ru.netology.ui

import android.view.View
import ru.netology.domain.model.Flight

interface OnFlightItemClickListener {
    fun onItemClicked(flight: Flight, view: View)
    fun onLikeClicked(flightId: String)
}