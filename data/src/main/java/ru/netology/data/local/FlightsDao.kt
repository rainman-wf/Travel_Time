package ru.netology.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.netology.data.local.entity.FlightEntity
import ru.netology.data.local.entity.FlightsWithSeatsEntity
import ru.netology.data.local.entity.LocalLikedFlightEntity
import ru.netology.data.local.entity.SeatEntity

@Dao
interface FlightsDao {

    @Transaction
    @Upsert
    suspend fun insert(flight: List<FlightEntity>, seats: List<SeatEntity>)

    @Transaction
    @Query("SELECT * FROM flights")
    fun getAll(): Flow<List<FlightsWithSeatsEntity>>

    @Insert
    suspend fun like(likedFlight: LocalLikedFlightEntity)

    @Delete
    suspend fun unlike(likedFlight: LocalLikedFlightEntity)

    @Transaction
    @Query("SELECT * FROM flights WHERE search_token = :id")
    suspend fun getFlightById(id: String) : FlightsWithSeatsEntity?

    @Query("UPDATE flights SET start_location_code=start_location_code")
    suspend fun trigger()

}