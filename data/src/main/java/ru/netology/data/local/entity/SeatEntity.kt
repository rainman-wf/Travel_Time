package ru.netology.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "seats",
    foreignKeys = [
        ForeignKey(
            entity = FlightEntity::class,
            parentColumns = ["search_token"],
            childColumns = ["flight_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["flight_id", "passenger_type"]
)
data class SeatEntity(
    @ColumnInfo(name = "flight_id") val flightId: String,
    @ColumnInfo(name = "passenger_type") val passengerType: String,
    val count: Int

)
