package ru.netology.ui

interface OnFlightItemClickListener {
    fun onItemClicked(flight: LikableFlight)
    fun onLikeClicked(flight: LikableFlight)
}