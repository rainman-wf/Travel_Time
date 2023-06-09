package ru.netology.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    ]
)
data class SeatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key") val localKey: Long,
    @ColumnInfo(name = "flight_id") val flightId: String,
    val count: Int,
    @ColumnInfo(name = "passenger_type") val passengerType: String
)
