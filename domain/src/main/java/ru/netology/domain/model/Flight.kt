package ru.netology.domain.model

import java.time.LocalDate

data class Flight(
    val endCity: String,
    val endDate: LocalDate,
    val endLocationCode: String,
    val price: Int,
    val searchToken: String,
    val seats: List<Seat>,
    val serviceClass: String,
    val startCity: String,
    val startDate: LocalDate,
    val startLocationCode: String,
    val isLiked: Boolean
)

