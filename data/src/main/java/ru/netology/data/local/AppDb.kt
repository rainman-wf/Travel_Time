package ru.netology.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.data.local.entity.FlightEntity
import ru.netology.data.local.entity.LocalLikedFlightEntity
import ru.netology.data.local.entity.SeatEntity

@Database(
    entities = [
        FlightEntity::class,
        LocalLikedFlightEntity::class,
        SeatEntity::class
    ],
    version = 1
)
abstract class AppDb : RoomDatabase() {
    abstract val flightsDao: FlightsDao
}