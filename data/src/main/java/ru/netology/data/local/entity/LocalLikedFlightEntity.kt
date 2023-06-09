package ru.netology.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "liked_flights",
    foreignKeys = [
        ForeignKey(
            entity = FlightEntity::class,
            parentColumns = ["search_token"],
            childColumns = ["flight_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LocalLikedFlightEntity(
    @PrimaryKey
    @ColumnInfo(name = "flight_id") val flightId: String
)
