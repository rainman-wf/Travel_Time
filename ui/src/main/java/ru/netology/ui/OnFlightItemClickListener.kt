package ru.netology.ui

import android.view.View

interface OnFlightItemClickListener {
    fun onItemClicked(flightId: String, view: View)
    fun onLikeClicked(flightId: String)
}