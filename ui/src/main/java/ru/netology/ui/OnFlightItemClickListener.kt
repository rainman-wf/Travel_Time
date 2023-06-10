package ru.netology.ui

interface OnFlightItemClickListener {
    fun onItemClicked(flightId: String)
    fun onLikeClicked(flightId: String)
}