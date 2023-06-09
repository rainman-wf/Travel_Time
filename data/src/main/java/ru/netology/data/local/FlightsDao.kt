package ru.netology.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.netology.data.local.entity.FlightEntity
import ru.netology.data.local.entity.FlightsWithSeatsEntity
import ru.netology.data.local.entity.LocalLikedFlightEntity
import ru.netology.data.local.entity.SeatEntity

@Dao
interface FlightsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlights(flight: List<FlightEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeats(seats: List<SeatEntity>)

    @Transaction
    suspend fun insert(flights: List<FlightEntity>, seats: List<SeatEntity>) {
        insertFlights(flights)
        insertSeats(seats)
    }

    @Transaction
    @Query("SELECT * FROM flights")
    fun getAll(): Flow<List<FlightsWithSeatsEntity>>

    @Insert
    fun like(likedFlight: LocalLikedFlightEntity)

    @Delete
    fun unlike(likedFlight: LocalLikedFlightEntity)

    @Query("SELECT flight_id FROM liked_flights")
    fun getAllLiked(): Flow<List<Long>>
}