package ru.netology.data.impl

import ru.netology.common.utils.fromDateTimeStringToLocalDate
import ru.netology.data.local.entity.FlightEntity
import ru.netology.data.local.entity.FlightsWithSeatsEntity
import ru.netology.data.local.entity.SeatEntity
import ru.netology.data.remote.response.FlightResponse
import ru.netology.domain.model.Flight
import ru.netology.domain.model.Seat

fun FlightsWithSeatsEntity.toModel(): Flight {
    return Flight(
        endCity = flight.endCity,
        endDate = flight.endDate,
        endLocationCode = flight.endLocationCode,
        price = flight.price,
        searchToken = flight.searchToken,
        seats = seats.map { it.toModel() },
        serviceClass = flight.serviceClass,
        startCity = flight.startCity,
        startDate = flight.startDate,
        startLocationCode = flight.startLocationCode,
    )
}

fun SeatEntity.toModel() =
    Seat(
        count = count,
        passengerType = passengerType
    )

fun Seat.toEntity(flightId: String) =
    SeatEntity(
        localKey = 0,
        flightId = flightId,
        count = count,
        passengerType = passengerType
    )

fun FlightResponse.toEntity() =
    FlightEntity(
        searchToken = searchToken,
        price = price,
        serviceClass = serviceClass,
        startCity = startCity,
        startDate = startDate.fromDateTimeStringToLocalDate(),
        startLocationCode = startLocationCode,
        endCity = endCity,
        endDate = endDate.fromDateTimeStringToLocalDate(),
        endLocationCode = endLocationCode,
    )