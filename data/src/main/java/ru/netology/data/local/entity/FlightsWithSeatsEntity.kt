package ru.netology.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FlightsWithSeatsEntity(
    @Embedded
    val flight: FlightEntity,
    @Embedded
    val liked: LocalLikedFlightEntity?,
    @Relation(
        parentColumn = "search_token",
        entityColumn = "flight_id"
    )
    val seats: List<SeatEntity>
)
