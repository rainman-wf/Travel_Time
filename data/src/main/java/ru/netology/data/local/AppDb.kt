package ru.netology.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.netology.data.impl.Converters
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
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract val flightsDao: FlightsDao
}