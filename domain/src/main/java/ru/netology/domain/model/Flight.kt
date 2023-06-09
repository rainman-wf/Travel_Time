package ru.netology.domain.model

data class Flight(
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