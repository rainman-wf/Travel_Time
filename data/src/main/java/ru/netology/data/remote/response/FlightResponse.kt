package ru.netology.data.remote.response

import ru.netology.domain.model.Seat

data class FlightResponse(
    val endCity: String,
    val endDate: String,
    val endLocationCode: String,
    val price: Int,
    val searchToken: String,
    val seats: List<Seat>,
    val serviceClass: String,
    val startCity: String,
    val startDate: String,
    val startLocationCode: String
)